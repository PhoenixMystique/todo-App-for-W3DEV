package com.devwithadi.todolist.Home.Adapter;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.devwithadi.todolist.Modules.group_list;
import com.devwithadi.todolist.R;
import com.devwithadi.todolist.Modules.tasksdata;

import java.util.List;

public class Groups_Adapter extends RecyclerView.Adapter<Groups_Adapter.Myholder> {
    private Context context;
    String Colors[];
    int count=0;

    private  List<group_list> Data_list;

    public Groups_Adapter(Context context, List<group_list> Data_list) {
        this.context = context;
        this.Data_list = Data_list;
     Colors  = new mcolors().groupcolor;

    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(Groups_Adapter.Myholder holder, final int position) {
        if (count==(Colors.length-1))
        { count=0; }
        holder.cardView.setCardBackgroundColor(Color.parseColor(Colors[count++]));
       holder.textView.setText("Group_ID "+String.valueOf(Data_list.get(position).getGroup_id()));
       



    }


    @Override
    public int getItemCount() {
        return Data_list.size();
    }

    static class Myholder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        Myholder(View itemView) {
            super(itemView);
           cardView= itemView.findViewById(R.id.task_dot);
            textView =  itemView.findViewById(R.id.group_id);

        }
    }

}
