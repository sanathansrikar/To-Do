package com.example.todo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TodoRVAdapter(private val context: Context, private val listener: ITodoRVAdapter): RecyclerView.Adapter<TodoRVAdapter.TodoViewHolder>() {

    private val allNotes = ArrayList<Todo>()

    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       val textView: TextView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton: ImageView = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val viewHolder = TodoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    override fun onBindViewHolder(holder: TodoRVAdapter.TodoViewHolder, position: Int) {
         val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }
    fun updateList(newList: List<Todo>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }

}

interface ITodoRVAdapter {
    fun onItemClicked(note: Todo)
}