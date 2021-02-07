package what.the.rxkotlin.android.data

/**
 * Created by jongkook on 2021.01.04
 */
data class UpdateItem(
    var name: String? = null,
    var job: String? = null
) {
    override fun toString(): String {
        return "UpdateItem(name=$name, job=$job)"
    }
}