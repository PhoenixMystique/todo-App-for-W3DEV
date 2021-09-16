package com.devwithadi.todolist.Home.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.devwithadi.todolist.Home.Interface.OnBannerItemClickListener
import com.devwithadi.todolist.Modules.group_list
import com.devwithadi.todolist.R

class Groups_Adapter(context: Context?, private val Data_list: List<group_list>) : RecyclerView.Adapter<Groups_Adapter.Myholder>() {
    private val context: Context? = null
    var Colors: Array<String>
    var count = 0
    private val mOnClickListener = View.OnClickListener { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myholder {
        return Myholder(LayoutInflater.from(parent.context).inflate(R.layout.group_layout, parent, false))
    }

    override fun onBindViewHolder(holder: Myholder, position: Int) {
        if (count == Colors.size - 1) {
            count = 0
        }
        holder.cardView.setCardBackgroundColor(Color.parseColor(Colors[count++]))
        holder.textView.text = "Group_ID " + Data_list[position].group_id.toString()
    }

    override fun getItemCount(): Int {
        return Data_list.size
    }

    class Myholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView: CardView
        var textView: TextView

        init {
            cardView = itemView.findViewById(R.id.task_dot)
            textView = itemView.findViewById(R.id.group_id)
        }
    }

    init {
        Colors = mcolors().groupcolor
    }
}