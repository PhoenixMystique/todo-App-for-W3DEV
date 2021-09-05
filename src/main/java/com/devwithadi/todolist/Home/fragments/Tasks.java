package com.devwithadi.todolist.Home.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.devwithadi.todolist.Home.Adapter.TodoListAdapter;
import com.devwithadi.todolist.Home.Activity.HomeActivity;
import com.devwithadi.todolist.Modules.Vertical_recyclerSpacer;
import com.devwithadi.todolist.R;

import java.util.List;


public class Tasks extends Fragment {
    List<com.devwithadi.todolist.Modules.tasksdata> tasksdata;
    TodoListAdapter tasksadapter;
    RecyclerView recyclerView;
 LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasksdata= HomeActivity.Task_data;
        System.out.println("dasdas"+tasksdata.size());
        System.out.println("errror");
        Toast.makeText(getContext(), String.valueOf(tasksdata.size()), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.recycleview);
        linearLayoutManager=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tasksadapter=new TodoListAdapter(getContext(),tasksdata);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new Vertical_recyclerSpacer(20));
        recyclerView.setAdapter(tasksadapter);
        Toast.makeText(getContext(), "oncreated", Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }
}