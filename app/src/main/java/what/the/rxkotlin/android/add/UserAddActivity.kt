package what.the.rxkotlin.android.add

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import what.the.rxkotlin.android.databinding.ActivityUserAddBinding

class UserAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtAddJob.setOnEditorActionListener { v, actionId, event ->
            var handled = false
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                handled = true
            }
            Log.i(this.localClassName, "* * * setOnEditorActionListener : $handled")
            return@setOnEditorActionListener handled
        }
    }
}