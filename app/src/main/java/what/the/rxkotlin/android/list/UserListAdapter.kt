package what.the.rxkotlin.android.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.Subject
import what.the.rxkotlin.android.data.DataItem
import what.the.rxkotlin.android.databinding.ItemUserBinding


/**
 * Created by jongkook on 2021.01.02
 */
class UserListAdapter(
    // 컨텍스트의 인스턴스르 생성자 매개 변수로 사용
    // 이 컨텍스트를 사용해 Inflater의 인스턴스를 가져왔으며
    // 이 인스턴스는 onCreateViewHolder 메서드 내부의 레이아웃을 확장하는데 사용
    private val context: Context,
    // val 매개변수로 람다를 사용. 이 람다는 DataItem의 인스턴스를 매개변수로 입력받고 Unit을 반환
//    val onItemClick: (DataItem?) -> Unit = {}
    val onClickItemSubject: Subject<Pair<View, DataItem>>
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    // onBindViewHodler 함수 내부에서 이를 ViewHolder 인스턴스로 전달
    private val userList = arrayListOf<DataItem>()

    // 어댑터에 새 목록을 할당하는데 사용
    // UserListActivity의 fetchUserList 메서드에 의해 호출돼야 한다.
    // fetchUserList : REST API로부터 목록을 가져오는 일  -> 결과를 어댑터로 전달
    fun setDataSet(list: List<DataItem>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemBinding =
            ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindView(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: DataItem) {
            with(itemView) {
                binding.txtId.text = item.id.toString()
                binding.txtFirstName.text = item.firstName.toString()
                binding.txtLastName.text = item.lastName.toString()
                binding.txtEmail.text = item.email.toString()

                /*
                아이템을 클릭할 때마다 onItemClick 람다가 호출. 이는 UserListActivity에서 전달된다.
                생성자에 Subject 인스턴스
                itemView를 클릭하면 Subject의 onNext 이벤트를 호출
                Pair를 사용해 itemView와 item 인스턴스를 전달
                setOnClickListener(콜백) --> itemView.clicks(리액티브)
                 */
                itemView.clicks()
                    .subscribeBy {
                        onClickItemSubject.onNext(Pair(itemView, item))
                    }

//                setOnClickListener {
////                    this@UserListAdapter.onItemClick(item)
//                    onClickUserSubject.onNext(Pair(itemView, item))
//                }
            }
        }
    }
}