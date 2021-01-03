package what.the.rxkotlin.android.data

import com.google.gson.annotations.SerializedName

data class DataItem(
	@SerializedName("last_name")
	val lastName: String? = null,
	val id: Int? = null,
	val avatar: String? = null,
	@SerializedName("first_name")
	val firstName: String? = null,
	val email: String? = null
)
