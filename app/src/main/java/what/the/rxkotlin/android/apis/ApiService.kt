package what.the.rxkotlin.android.apis

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import what.the.rxkotlin.android.apis.response.ListUsersResponse
import what.the.rxkotlin.android.util.Constants

/**
 * Created by jongkook on 2021.01.04
 */
interface ApiService {
    @GET(Constants.GET_USERS)
    fun getUsers(): Observable<ListUsersResponse>

//    @POST(Constants.CREATE)
//    fun create
}