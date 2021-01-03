package what.the.rxkotlin.android.data

import what.the.rxkotlin.android.apis.response.BaseApiResponse

open class ListUsersResponse(
    val perPage: Int? = null,
    val total: Int? = null,
    val data: List<DataItem?>? = null,
    val page: Int? = null,
    val totalPages: Int? = null,
) : BaseApiResponse()