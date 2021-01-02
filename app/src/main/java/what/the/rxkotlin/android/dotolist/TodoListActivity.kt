package what.the.rxkotlin.android.dotolist

import android.content.Intent
import android.os.Bundle
import what.the.rxkotlin.android.BaseActivity
import what.the.rxkotlin.android.R
import what.the.rxkotlin.android.databinding.ActivityTodoListBinding

class TodoListActivity : BaseActivity() {

//    lateinit var adapter: ToDoAdapter

    // 액티비티에서 사용할 레이아웃의 뷰 바인딩 클래스
    private lateinit var binding: ActivityTodoListBinding

    private val INTENT_EDIT_TODO: Int = 100
    private val INTENT_ADD_TODO: Int = 101

    override fun onCreateBaseActivity(savedInstanceState: Bundle?) {
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fabAddTodo.setOnClickListener {
//            val intent = Intent(this, TodoDetailsActivity)
//            startActivityForResult(intent, INTENT_ADD_TODO)
        }
    }

}