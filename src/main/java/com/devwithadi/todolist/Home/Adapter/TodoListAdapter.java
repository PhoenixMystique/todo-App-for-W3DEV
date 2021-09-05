package com.devwithadi.todolist.Home.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.devwithadi.todolist.R;
import com.devwithadi.todolist.Modules.tasksdata;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.Myholder> {
    private Context context;

    private  List<tasksdata> Data_list;
    private String Colors[] ;
    private int count=0;

    public TodoListAdapter(Context context, List<tasksdata> Data_list) {
        this.context = context;
        this.Data_list = Data_list;
        Colors = new mcolors().taskcolor;
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false));
    }
    @Override
    public void onBindViewHolder(TodoListAdapter.Myholder holder, final int position) {

        if (count==(Colors.length-1))
        { count=0; }
        holder.cardView.setCardBackgroundColor(Color.parseColor(Colors[count++]));

        holder.Title.setText(Data_list.get(position).getTitle());
        Toast.makeText(context, Data_list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Edited", Toast.LENGTH_SHORT).show();
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
            return Data_list.size();
    }

    static class Myholder extends RecyclerView.ViewHolder {
        ImageView delete;
        TextView Title;
        ImageView edit;
        CardView cardView;
        Myholder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.task_dot);
            Title = itemView.findViewById(R.id.group_id);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            edit = (ImageView) itemView.findViewById(R.id.edit);


        }
    }

}
