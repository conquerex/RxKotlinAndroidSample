package what.the.rxkotlin.android.detail

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import what.the.rxkotlin.android.BaseActivity
import what.the.rxkotlin.android.R
import what.the.rxkotlin.android.apis.ApiClient
import what.the.rxkotlin.android.data.DataItem
import what.the.rxkotlin.android.data.UpdateItem
import what.the.rxkotlin.android.databinding.ActivityUserDetailBinding
import what.the.rxkotlin.android.util.Constants
import java.util.*

class UserDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityUserDetailBinding

    var dataItem: DataItem? = null
    var isEditing = false

    override fun onCreateBaseActivity(savedInstanceState: Bundle?) {
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataItem = intent?.getParcelableExtra(Constants.INTENT_USER_DETAIL) as? DataItem
        binding.toolbar.title = "${dataItem?.firstName} ${dataItem?.lastName}"
        setSupportActionBar(binding.toolbar)


        binding.layoutContentUserDetails.txtDetailId.text = dataItem?.id?.toString()
        binding.layoutContentUserDetails.txtDetailFirstName.text = dataItem?.firstName
        binding.layoutContentUserDetails.txtDetailLastName.setText(dataItem?.lastName)
        binding.layoutContentUserDetails.txtDetailEmail.text = dataItem?.email

        binding.fabEditUser.clicks().subscribeBy {
            if (isEditing) {
                saveData()
            } else {
                startEdit()
            }
        }
    }

    private fun startEdit() {
        isEditing = true

        binding.layoutContentUserDetails.txtDetailLastName.inputType =
            InputType.TYPE_TEXT_FLAG_CAP_SENTENCES or InputType.TYPE_CLASS_TEXT
        binding.layoutContentUserDetails.txtDetailJob.inputType =
            InputType.TYPE_TEXT_FLAG_CAP_SENTENCES or InputType.TYPE_CLASS_TEXT

        /**
         * 필요한 기능은 아니지만 Rx를 활용한 클릭 이벤트 실습을 위한 DatePickerDialog 구현
         */
        binding.layoutContentUserDetails.txtDetailUpdatedAt
            .clicks()
            .subscribeBy {
                val calendar = Calendar.getInstance()
                val dp = DatePickerDialog(
                    this,
                    { _, year, month, dayOfMonth ->
                        binding.layoutContentUserDetails.txtDetailUpdatedAt.text =
                            "$year/${month + 1}/$dayOfMonth"
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                dp.show()
            }

        binding.layoutContentUserDetails.txtDetailLastName.requestFocus()

        binding.fabEditUser.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_save))
    }

    /**
     * get api에 나오는 정보가 수정되는 update가 아님
     * 어디까지나 fake api이기 때문에 REST하게 작동하는 것만 확인
     */
    private fun saveData() {
        val item = UpdateItem()
        item.name = binding.layoutContentUserDetails.txtDetailLastName.text.toString()
        item.job = binding.layoutContentUserDetails.txtDetailJob.text.toString()

        ApiClient()
            .getApiService().updateUser(dataItem?.id!!, item)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    if (it != null) {
                        stopEdit()
                        val res = """
                        User info successfully saved
                        Name    : ${it.name}
                        Job     : ${it.job}
                        Updated : ${it.updatedAt}
                        """.trimIndent()
                        Toast.makeText(this, res, Toast.LENGTH_SHORT).show()
                        binding.layoutContentUserDetails.txtDetailUpdatedAt.text = it.updatedAt
                        setResult(Activity.RESULT_OK)
                    } else {
                        Toast.makeText(
                            this,
                            "Couldn't save this info, please try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                onError = {
                    Toast.makeText(
                        this,
                        "Couldn't save this user, please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                    stopEdit()
                    it.printStackTrace()
                }
            )
    }

    private fun stopEdit() {
        isEditing = false

        binding.layoutContentUserDetails.txtDetailLastName.inputType =
            InputType.TYPE_NULL or InputType.TYPE_CLASS_TEXT
        binding.layoutContentUserDetails.txtDetailJob.inputType =
            InputType.TYPE_NULL or InputType.TYPE_CLASS_TEXT

        binding.fabEditUser.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_edit))
    }

    override fun onBackPressed() {
        if (!isEditing) {
            super.onBackPressed()
        } else {
            stopEdit()
        }
    }
}