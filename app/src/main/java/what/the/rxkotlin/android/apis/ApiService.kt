package what.the.rxkotlin.android.apis

import com.rivuchk.todoapplication.utils.Constants
import io.reactivex.rxjava3.core.Observable

import retrofit2.http.GET
import what.the.rxkotlin.android.apis.response.ListUsersResponse

/**
 * Created by jongkook on 2021.01.04
 */
interface ApiService {
    @GET(Constants.GET_USERS)
    fun getUsers(): Observable<ListUsersResponse>

//    @POST(Constants.CREATE)
//    fun create
}