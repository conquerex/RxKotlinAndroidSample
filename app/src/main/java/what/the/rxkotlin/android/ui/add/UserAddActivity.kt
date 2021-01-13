package what.the.rxkotlin.android.ui.add

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.kotlin.subscribeBy
import what.the.rxkotlin.android.BaseActivity
import what.the.rxkotlin.android.data.UpdateItem
import what.the.rxkotlin.android.databinding.ActivityUserAddBinding
import what.the.rxkotlin.android.repository.RepositoryImpl

class UserAddActivity : BaseActivity() {
    private lateinit var binding: ActivityUserAddBinding

    private val repository by lazy {
        RepositoryImpl()
    }

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

        repository.createUser(item)
            .subscribeBy(
                onSuccess = {
                    val res = """
                        User info successfully saved
                        Name    : ${it.name}
                        Job     : ${it.job}
                        """.trimIndent()
                    Toast.makeText(this, res, Toast.LENGTH_SHORT).show()
                    Log.i(this.localClassName, res)
                    setResult(Activity.RESULT_OK)
                    finish()
                },
                onError = {
                    it.printStackTrace()
                    Toast.makeText(this, "정상적으로 진행할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            )
    }

}