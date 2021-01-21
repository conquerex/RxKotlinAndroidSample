package what.the.rxkotlin.android

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import kotlin.math.roundToInt

class MyTests : FunSpec({
    test("String length should return the length of the string") {
        "sammy".length shouldBe 5
        "".length shouldBe 0
    }

//    val sample = "abcd"
//    sample should startWith("ab")
})

class MyTests2 : ShouldSpec({
    context("String.length") {
        should("return the length of the string") {
            "sammy".length shouldBe 5
            "".length shouldBe 0
        }
    }

    context("sample") {
        val sample = "abcd"
        sample should startWith("ab")
    }
})

class MyTests3 : BehaviorSpec({
    given("a broomstick") {
        `when`("I sit on it") {
            then("I should be able to fly") {
                // test code
                val list = listOf("a", "b", "c")
                list.contains("b")
            }
        }
        `when`("I throw it away") {
            then("it should come back") {
                // test code
                val list = listOf("a", "b", "c")
                list.contains("b")
            }
        }
    }
})

class MyTests4 : BehaviorSpec({
    given("100점이 만점인 상황에서") {
        val totalMarks = 100
        `when`("학생의 점수가") {
            and("90점 이상이라면") {
                val obtainedMarks = 92
                then("등급은 A") {
                    getGrade(obtainedMarks, totalMarks) shouldBe "A"
                }
            }

            and("80점 이상 90점 미만이라면") {
                val obtainedMarks = 88
                then("등급은 B") {
                    getGrade(obtainedMarks, totalMarks) shouldBe "B"
                }
            }

            and("70점 이상 80점 미만이라면") {
                val obtainedMarks = 77
                then("등급은 C") {
                    getGrade(obtainedMarks, totalMarks) shouldBe "C"
                }
            }

            and("60점 이상 70점 미만이라면") {
                val obtainedMarks = 66
                then("등급은 D") {
                    getGrade(obtainedMarks, totalMarks) shouldBe "D"
                }
            }

            and("60점 미만이라면") {
                val obtainedMarks = 34
                then("등급은 F") {
                    getGrade(obtainedMarks, totalMarks) shouldBe "F"
                }
            }
        }
    }
})


fun getGrade(obtainedMarks: Int, totalMarks: Int): String {
    val percentage = getPercentage(obtainedMarks, totalMarks)
    return when {
        percentage >= 90 -> "A"
        percentage in 80..89 -> "B"
        percentage in 70..79 -> "C"
        percentage in 60..69 -> "D"
        else -> "F"
    }
}

private fun getPercentage(obtainedMarks: Int, totalMarks: Int): Int {
    return (obtainedMarks / totalMarks.toFloat() * 100).roundToInt()
}