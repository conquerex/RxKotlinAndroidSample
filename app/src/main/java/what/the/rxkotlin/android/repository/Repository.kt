package what.the.rxkotlin.android.repository

import io.reactivex.rxjava3.core.Single
import what.the.rxkotlin.android.data.DataItem
import what.the.rxkotlin.android.data.UpdateItem

interface Repository {
    fun getUsers(): Single<List<DataItem>>
    fun updateUser(id: Int, updateItem: UpdateItem): Single<UpdateItem>
    fun createUser(updateItem: UpdateItem): Single<UpdateItem>
}