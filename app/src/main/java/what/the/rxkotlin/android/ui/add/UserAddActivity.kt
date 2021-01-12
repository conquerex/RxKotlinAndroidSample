package what.the.rxkotlin.android.ui.add

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import what.the.rxkotlin.android.BaseActivity
import what.the.rxkotlin.android.apis.ApiClient
import what.the.rxkotlin.android.data.UpdateItem
import what.the.rxkotlin.android.databinding.ActivityUserAddBinding

class UserAddActivity : BaseActivity() {
    private lateinit var binding: ActivityUserAddBinding

    override fun onCreateBaseActivity(savedInstanceState: Bundle?) {
        binding = ActivityUserAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtAddJob.setOnEditorActionListener { _, actionId, _ ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                handled = true
                addUser()
            }

            // 자동으로 키보드 내리기
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            return@setOnEditorActionListener handled
        }

        binding.btnAddUser.clicks().subscribe {
            addUser()
        }
    }

    private fun addUser() {
        val item = UpdateItem()
        item.name = binding.txtAddName.text.toString()
        item.job = binding.txtAddJob.text.toString()

        ApiClient()
            .getApiService().createUser(item)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    val res = """
                        User info successfully saved
                        ID      : ${it.id}
                        Name    : ${it.name}
                        Job     : ${it.job}
                        Created : ${it.createdAt}
                        """.trimIndent()
                    Toast.makeText(this, res, Toast.LENGTH_SHORT).show()
                },
                onError = {
                    it.printStackTrace()
                    Toast.makeText(this, "정상적으로 진행할 수 없습니다.", Toast.LENGTH_SHORT).show()
                },
                onComplete = {
                    Log.i(this.localClassName, "* * * * Complete * * * *")
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            )
    }

}