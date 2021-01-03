package what.the.rxkotlin.android.apis

import com.rivuchk.todoapplication.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by jongkook on 2021.01.04
 * .
 * 레트로핏을 위한 builder 클래스
 */
class ApiClient {
    private var retrofit: Retrofit? = null

    enum class LogLevel {
        LOG_NOT_NEEDED,
        LOG_REQ_RES,
        LOG_REQ_RES_BODY_HEADER,
        LOG_REQ_RES_HEADERS_ONLY
    }

    /**
     * 생성을 위한 Retrofit 빌더를 반환
     * @param logLevel - to print the log of Request-Response
     * @return retrofit
     */
    fun getClient(logLevel: LogLevel): Retrofit {

        // RxJava 어댑터와 함께 OkHttp 로깅 인터셉터를 추가
        // 요청과 응답을 기록
        val interceptor = HttpLoggingInterceptor()
        when (logLevel) {
            LogLevel.LOG_NOT_NEEDED ->
                interceptor.level = HttpLoggingInterceptor.Level.NONE
            LogLevel.LOG_REQ_RES ->
                interceptor.level = HttpLoggingInterceptor.Level.BASIC
            LogLevel.LOG_REQ_RES_BODY_HEADER ->
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            LogLevel.LOG_REQ_RES_HEADERS_ONLY ->
                interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        }

        if (retrofit == null) {
            val client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .build()
        }

        return retrofit!!
    }

    fun getApiService(logLevel: LogLevel = LogLevel.LOG_REQ_RES_BODY_HEADER): ApiService =
        getClient(logLevel).create(ApiService::class.java)
}