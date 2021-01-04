package what.the.rxkotlin.android.apis

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import what.the.rxkotlin.android.apis.response.ListUsersResponse
import what.the.rxkotlin.android.apis.response.UserUpdateResponse
import what.the.rxkotlin.android.data.UpdateItem
import what.the.rxkotlin.android.util.Constants

/**
 * Created by jongkook on 2021.01.04
 */
interface ApiService {
    @GET(Constants.GET_USERS)
    fun getUsers(): Observable<ListUsersResponse>

    @PUT(Constants.UPDATE)
    fun updateUser(
//        @Path("id") id: Int, @Body json:String): Observable<UserUpdateResponse>
        @Path("id") id: Int, @Body updateItem: UpdateItem
    ): Observable<UserUpdateResponse>
}