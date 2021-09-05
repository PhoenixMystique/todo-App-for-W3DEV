package com.devwithadi.todolist.Modules;

import java.util.ArrayList;
import java.util.List;

public class group_list {

    private List<tasksdata> tasksdata;
   private int Group_id;

    public group_list(List<com.devwithadi.todolist.Modules.tasksdata> tasksdata, int group_id) {
        this.tasksdata = tasksdata;
        Group_id = group_id;
    }

    public List<com.devwithadi.todolist.Modules.tasksdata> getTasksdata() {
        return tasksdata;
    }

    public void setTasksdata(List<com.devwithadi.todolist.Modules.tasksdata> tasksdata) {
        this.tasksdata = tasksdata;
    }

    public int getGroup_id() {
        return Group_id;
    }

    public void setGroup_id(int group_id) {
        Group_id = group_id;
    }
}
