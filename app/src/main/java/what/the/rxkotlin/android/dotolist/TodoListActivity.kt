package what.the.rxkotlin.android.dotolist

import android.content.Intent
import android.os.Bundle
import what.the.rxkotlin.android.BaseActivity
import what.the.rxkotlin.android.databinding.ActivityTodoListBinding

class TodoListActivity : BaseActivity() {

    // ToDoAdapter 인스턴스를 만들어 rvToDoList의 어댑터로 설정
    lateinit var adapter: ToDoAdapter

    // 액티비티에서 사용할 레이아웃의 뷰 바인딩 클래스
    private lateinit var binding: ActivityTodoListBinding

    private val INTENT_EDIT_TODO: Int = 100
    private val INTENT_ADD_TODO: Int = 101

    override fun onCreateBaseActivity(savedInstanceState: Bundle?) {
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fabAddTodo.setOnClickListener {
//            val intent = Intent(this, AddTodoActivity)
//            startActivityForResult(intent, INTENT_ADD_TODO)
        }

        adapter = ToDoAdapter(this) {
            // adapter 인스턴스를 생성하는 동안 람다를 전달
            // 이 람다는 rvToDoList의 항목을 클릭할 때마다 호출
//            val intent = Intent(this, TodoDetailsActivity)
//            startActivityForResult(intent, INTENT_EDIT_TODO)
        }

        binding.rvToDoList.adapter = adapter

        // REST api에서 Todos 목록을 가져온다.
//        fetchTodoList()

    }

}