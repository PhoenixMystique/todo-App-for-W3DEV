package com.devwithadi.todolist.Home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devwithadi.todolist.Home.Activity.HomeActivity
import com.devwithadi.todolist.Home.Adapter.TodoListAdapter
import com.devwithadi.todolist.Modules.Vertical_recyclerSpacer
import com.devwithadi.todolist.Modules.tasksdata
import com.devwithadi.todolist.R

class Tasks : Fragment() {
    var tasksdata: List<tasksdata>? = null
    var tasksadapter: TodoListAdapter? = null
    var recyclerView: RecyclerView? = null
    var linearLayoutManager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasksdata = HomeActivity.Task_data
        println("dasdas" + tasksdata!!.size)
        println("errror")
        Toast.makeText(context, tasksdata!!.size.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycleview)
        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        tasksadapter = context?.let { tasksdata?.let { it1 -> TodoListAdapter(it, it1) } }
        recyclerView?.setLayoutManager(linearLayoutManager)
        recyclerView?.addItemDecoration(Vertical_recyclerSpacer(20))
        recyclerView?.setAdapter(tasksadapter)
        Toast.makeText(context, "oncreated", Toast.LENGTH_SHORT).show()
    }

    fun ChangeData(data: List<tasksdata?>?) {
        tasksadapter = context?.let { TodoListAdapter(it, data as List<tasksdata>) }
        recyclerView!!.adapter = tasksadapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }
}