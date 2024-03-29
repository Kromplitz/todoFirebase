package com.example.todofirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskListAdapter (var items:List<Holder> = emptyList()): RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val listItemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item_layout, parent, false)
        return TaskViewHolder(listItemView)
    }
    override fun getItemCount(): Int = items.size
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.subject.text = items[position].subject
        holder.todo.text = items[position].todo
    }
}
class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val subject: TextView = itemView.findViewById(R.id.subject)
    val todo: TextView = itemView.findViewById(R.id.todo)
}