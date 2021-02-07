package what.the.rxkotlin.android

import com.google.gson.Gson
import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.FunSpec
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import what.the.rxkotlin.android.apis.ApiService
import what.the.rxkotlin.android.apis.response.ListUsersResponse
import what.the.rxkotlin.android.data.DataItem
import java.util.concurrent.TimeUnit


class MockWebServerTest : FunSpec() {

    private lateinit var server: MockWebServer

    override fun beforeSpec(spec: Spec) {
        server = MockWebServer()
        server.start()
    }

    override fun afterSpec(spec: Spec) {
        server.shutdown()
    }

    init {
        test("Test MockServer") {
            // baseUrl : http://localhost:port번호/v1/
            val baseUrl = server.url("/v1/")

            val client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .build()

            // 인터페이스 구현
            val service = retrofit.create(ApiService::class.java)

            val dataitem1 = DataItem(lastName = "Sample1", firstName = "Test1", id = 1)
            val dataitem2 = DataItem(lastName = "Sample2", firstName = "Test2", id = 2)
            val list = listOf(dataitem1, dataitem2)
            val res = ListUsersResponse(perPage = 1, data = list)

            val response = MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setResponseCode(200)
                .setBody(Gson().toJson(res))

            server.enqueue(response)

            service.getUsers()
                .test()
                .awaitDone(2000, TimeUnit.MILLISECONDS)
                .assertNoErrors()
                .assertValue {
                    it.data[0].firstName == "Test1"
                }
        }
    }
}