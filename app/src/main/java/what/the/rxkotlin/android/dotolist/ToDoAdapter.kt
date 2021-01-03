package what.the.rxkotlin.android.dotolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import what.the.rxkotlin.android.R
import what.the.rxkotlin.android.data.DataItem


/**
 * Created by jongkook on 2021.01.02
 */
class ToDoAdapter(
    private val context: Context,
    val onItemClick: (DataItem?) -> Unit = {}
) : RecyclerView.Adapter<ToDoAdapter.TodoViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val todoList = arrayListOf<DataItem>()

    fun setDataSet(list: List<DataItem>) {
        todoList.clear()
        todoList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(inflater.inflate(R.layout.item_todo, parent, false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindView(todoList[position])
    }

    override fun getItemCount(): Int = todoList.size

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: DataItem) {
            //
        }
    }
}