package what.the.rxkotlin.android.apis

import com.rivuchk.todoapplication.utils.Constants

import retrofit2.http.GET
import retrofit2.Call
import what.the.rxkotlin.android.data.ListUsersResponse

/**
 * Created by jongkook on 2021.01.04
 */
interface ApiService {
    @GET(Constants.GET_USERS)
    fun getUsers(): Call<ListUsersResponse>

//    @POST(Constants.CREATE)
//    fun create
}