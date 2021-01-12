package what.the.rxkotlin.android.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import what.the.rxkotlin.android.apis.ApiClient
import what.the.rxkotlin.android.data.DataItem

// todo : MVVM 적용시 더욱 명확히 분리할 것!
class RepositoryImpl : Repository {
    override fun getUsers(): Single<List<DataItem>> {
        /**
         * api의 옵저버블을 구독하고 데이터가 도착하면 어댑터에 데이터를 할당
         * 데이터가 할당하기 전에 오류 코드를 확인하도록 설계해야 하지만 지금도 잘 동작은 함
         */
        return ApiClient()
            .getApiService().getUsers()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }
    }

}