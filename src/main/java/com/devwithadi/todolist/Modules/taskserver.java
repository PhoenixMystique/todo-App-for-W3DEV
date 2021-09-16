package com.devwithadi.todolist.Modules;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface taskserver {

    @GET("posts")
    Call<List<tasksdata>> getAllPosts();


}