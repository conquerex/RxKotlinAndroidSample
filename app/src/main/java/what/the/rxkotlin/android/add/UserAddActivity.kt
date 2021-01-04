package what.the.rxkotlin.android.add

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import what.the.rxkotlin.android.BaseActivity
import what.the.rxkotlin.android.databinding.ActivityUserAddBinding

class UserAddActivity : BaseActivity() {
    private lateinit var binding: ActivityUserAddBinding

    override fun onCreateBaseActivity(savedInstanceState: Bundle?) {
        binding = ActivityUserAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.txtAddJob.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                handled = true
            }
            Log.i(this.localClassName, "* * * setOnEditorActionListener : $handled")

            // 자동으로 키보드 내리기
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            return@setOnEditorActionListener handled
        }

    }

}