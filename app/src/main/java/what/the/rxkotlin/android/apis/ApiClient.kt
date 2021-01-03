package what.the.rxkotlin.android.apis

import com.rivuchk.todoapplication.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by jongkook on 2021.01.04
 */
class ApiClient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {

        if (retrofit == null) {
            val client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        return retrofit!!
    }

//    fun getApiService() =
//        getClient().create()
}