package what.the.rxkotlin.android.apis.response

import what.the.rxkotlin.android.data.DataItem

open class ListUsersResponse(
    val perPage: Int? = null,
    val total: Int? = null,
    val data: List<DataItem>,
    val page: Int? = null,
    val totalPages: Int? = null,
) : BaseApiResponse()
