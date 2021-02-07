package what.the.rxkotlin.android

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class KotestSampleTests : FunSpec({
    test("String length should return the length of the string") {
        "sammy".length shouldBe 5
        "".length shouldBe 0
    }
})
