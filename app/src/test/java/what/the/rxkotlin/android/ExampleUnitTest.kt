package what.the.rxkotlin.android

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestCase
import io.kotest.fp.success
import io.kotest.matchers.nulls.shouldNotBeNull
import kotlinx.coroutines.awaitAll
import what.the.rxkotlin.android.apis.ApiClient
import what.the.rxkotlin.android.apis.ApiService
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : FunSpec() {
    private lateinit var apiService: ApiService

    // 본 코드 기준으로 FunSpec형 Test를 시작하기 전에 1회만 실행
    override fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        println("beforeSpec #####")
        apiService = ApiClient().getApiService()
    }

    // 개별 test()전마다 1회씩 실행
    override fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        println("beforeTest >>>>>")
    }

    init {
        test("getUsers API") {
            val testObserver = apiService.getUsers()
                .test()
                .awaitDone(2000, TimeUnit.MILLISECONDS)
            testObserver.assertNoErrors()
                .assertComplete()
        }
    }
}
