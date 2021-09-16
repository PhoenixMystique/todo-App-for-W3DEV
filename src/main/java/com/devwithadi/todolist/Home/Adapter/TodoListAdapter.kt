package com.devwithadi.todolist.Home.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.devwithadi.todolist.Modules.tasksdata
import com.devwithadi.todolist.R

class TodoListAdapter(private val context: Context, private val Data_list: List<tasksdata>) : RecyclerView.Adapter<TodoListAdapter.Myholder>() {
    private val Colors: Array<String>
    private var count = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myholder {
        return Myholder(LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false))
    }

    override fun onBindViewHolder(holder: Myholder, position: Int) {
        if (count == Colors.size - 1) {
            count = 0
        }
        holder.cardView.setCardBackgroundColor(Color.parseColor(Colors[count++]))
        holder.Title.text = Data_list[position].title
        Toast.makeText(context, Data_list[position].title, Toast.LENGTH_SHORT).show()
        holder.edit.setOnClickListener { Toast.makeText(context, "Edited", Toast.LENGTH_SHORT).show() }
        holder.delete.setOnClickListener { Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show() }
    }

    override fun getItemCount(): Int {
        return Data_list.size
    }

    class Myholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var delete: ImageView
        var Title: TextView
        var edit: ImageView
        var cardView: CardView

        init {
            cardView = itemView.findViewById(R.id.task_dot)
            Title = itemView.findViewById(R.id.group_id)
            delete = itemView.findViewById<View>(R.id.delete) as ImageView
            edit = itemView.findViewById<View>(R.id.edit) as ImageView
        }
    }

    init {
        Colors = mcolors().taskcolor
    }
}