package what.the.rxkotlin.android.repository

import io.reactivex.rxjava3.core.Single
import what.the.rxkotlin.android.data.DataItem

interface Repository {
    fun getUsers(): Single<List<DataItem>>
}