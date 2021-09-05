package com.devwithadi.todolist.Home.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.devwithadi.todolist.Home.Adapter.Groups_Adapter;
import com.devwithadi.todolist.Home.fragments.Tasks;
import com.devwithadi.todolist.Modules.group_list;
import com.devwithadi.todolist.Modules.tasksdata;
import com.devwithadi.todolist.Modules.taskserver;
import com.devwithadi.todolist.R;
import com.devwithadi.todolist.Modules.horizontal_recycleSpacer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {
private CardView cardView;
private RecyclerView recyclerView;
Groups_Adapter groups_adapter;
LinearLayoutManager linearLayoutManager;
public  List<com.devwithadi.todolist.Modules.tasksdata> tasks;
    public static List<com.devwithadi.todolist.Modules.tasksdata> Task_data;

public static List<group_list> Group_lists;
DrawerLayout drawerLayout;
private static Retrofit retrofit;
ActionBarDrawerToggle actionBarDrawerToggle;
    private static final String URL="https://jsonplaceholder.typicode.com";
int count=1;



    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tasks= new ArrayList<>();
        Group_lists= new ArrayList<>();
        getRetrofitInstance();
        cardView = findViewById(R.id.cardview);
        taskserver taskserver = retrofit.create(com.devwithadi.todolist.Modules.taskserver.class);
        Call<List<com.devwithadi.todolist.Modules.tasksdata>> data = taskserver.getAllPosts();
        data.enqueue(new Callback<List<com.devwithadi.todolist.Modules.tasksdata>>() {
            @Override
            public void onResponse(Call<List<com.devwithadi.todolist.Modules.tasksdata>> call, Response<List<com.devwithadi.todolist.Modules.tasksdata>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    System.out.println("problem");
                }
                int groupid=0;
                System.out.println("sadsad"+response.body().size());
                List<tasksdata> data = response.body();
                for(tasksdata tasksdata:data){
                    if(groupid==0){
                       groupid= tasksdata.getUserID();
                    }
                    if (groupid!=tasksdata.getUserID()){
                        Group_lists.add(new group_list(tasks,groupid));
                        groupid=tasksdata.getUserID();
                        tasks.clear();
                    }
                    else {
                        tasks.add(new tasksdata(tasksdata.getUserID(),tasksdata.getId(),tasksdata.getTitle(),tasksdata.getBody()));
                    }
                    System.out.println(tasksdata.getUserID());
                }
attach();
            }

            @Override
            public void onFailure(Call<List<com.devwithadi.todolist.Modules.tasksdata>> call, Throwable t) {
                System.out.println("problem2");
            }
        });

        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.dark));
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
public void  attach(){
     GenerView();
    Task_data=Group_lists.get(0).getTasksdata();
    Tasks task = new Tasks();
    FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.fragmentContainerView,task);
    fragmentTransaction.commit();
    cardView.setVisibility(View.GONE);



}
private void GenerView(){
    recyclerView =findViewById(R.id.gener);
    linearLayoutManager =new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL,false);
    groups_adapter = new Groups_Adapter(HomeActivity.this,Group_lists);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.addItemDecoration(new horizontal_recycleSpacer(20));
    recyclerView.setAdapter(groups_adapter);
}


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}