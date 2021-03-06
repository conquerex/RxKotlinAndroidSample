package what.the.rxkotlin.android.ui.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import what.the.rxkotlin.android.BaseActivity
import what.the.rxkotlin.android.data.DataItem
import what.the.rxkotlin.android.databinding.ActivityUserListBinding
import what.the.rxkotlin.android.repository.RepositoryImpl
import what.the.rxkotlin.android.ui.add.UserAddActivity
import what.the.rxkotlin.android.ui.detail.UserDetailActivity
import what.the.rxkotlin.android.util.Constants


class UserListActivity : BaseActivity() {

    // UserListAdapter 인스턴스를 만들어 rvUserList의 어댑터로 설정
    lateinit var adapter: UserListAdapter

    // 액티비티에서 사용할 레이아웃의 뷰 바인딩 클래스
    private lateinit var binding: ActivityUserListBinding

    private val repository by lazy {
        RepositoryImpl()
    }

    override fun onCreateBaseActivity(savedInstanceState: Bundle?) {
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fabAddUser.clicks().subscribeBy {
            val intent = Intent(this, UserAddActivity::class.java)
            startActivityForResult(intent, Constants.CODE_ADD_USER)
        }

        val onClickUserSubject = PublishSubject.create<Pair<View, DataItem>>()
        onClickUserSubject.filter {
            it != null
        }.subscribeBy {
            val intent = Intent(this, UserDetailActivity::class.java)
            intent.putExtra(Constants.INTENT_USER_DETAIL, it.second)
            startActivityForResult(intent, Constants.CODE_GET_USER)
        }

//        adapter = UserListAdapter(this) {
//            // adapter 인스턴스를 생성하는 동안 람다를 전달
//            // 이 람다는 rvUserList의 항목을 클릭할 때마다 호출
//            val intent = Intent(this, UserDetailsActivity)
//            startActivityForResult(intent, INTENT_EDIT_USER)
//        }

        adapter = UserListAdapter(this, onClickUserSubject)
        binding.rvUserList.adapter = adapter

        // REST api에서 Users 목록을 가져온다.
        fetchUserList()
    }

    private fun fetchUserList() {
        repository.getUsers()
            .subscribeBy(
                onSuccess = {
                    if (it.isEmpty()) {
                        Log.i(this.localClassName, "Data(List users) is null")
                        Toast.makeText(this, "더 이상 불러올 데이터가 없습니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        adapter.setDataSet(it)
                        Log.i(this.localClassName, "* * * * Complete * * * *")
                        Toast.makeText(this, "정상적으로 데이터를 가지고 왔습니다.", Toast.LENGTH_SHORT).show()
                    }
                },
                onError = {
                    it.printStackTrace()
                    Toast.makeText(this, "데이터를 가지고 올 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == Constants.CODE_GET_USER || requestCode == Constants.CODE_ADD_USER)
            && resultCode == Activity.RESULT_OK
        ) {
            fetchUserList()
        }
    }
}