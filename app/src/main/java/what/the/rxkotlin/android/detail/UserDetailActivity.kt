package what.the.rxkotlin.android.detail

import android.os.Bundle
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.kotlin.subscribeBy
import what.the.rxkotlin.android.BaseActivity
import what.the.rxkotlin.android.data.DataItem
import what.the.rxkotlin.android.databinding.ActivityUserDetailBinding
import what.the.rxkotlin.android.util.Constants

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
        binding.layoutContentUserDetails.txtDetailFirstName.setText(dataItem?.firstName)
        binding.layoutContentUserDetails.txtDetailLastName.setText(dataItem?.lastName)
        binding.layoutContentUserDetails.txtDetailEmail.text = dataItem?.email

        binding.fabEditUser.clicks().subscribeBy { _ ->
            if (isEditing) {
//                saveData()
            } else {
//                startEdit()
            }
        }
    }

    override fun onBackPressed() {
        if (!isEditing) {
            super.onBackPressed()
        } else {
//            stopEdit()
        }
    }
}