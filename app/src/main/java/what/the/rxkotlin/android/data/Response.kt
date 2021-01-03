package what.the.rxkotlin.android.data

data class Response(
	val perPage: Int? = null,
	val total: Int? = null,
	val data: List<DataItem?>? = null,
	val page: Int? = null,
	val totalPages: Int? = null,
	val support: Support? = null
)
