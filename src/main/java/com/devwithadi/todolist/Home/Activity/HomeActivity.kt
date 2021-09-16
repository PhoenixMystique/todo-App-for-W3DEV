package com.devwithadi.todolist.Home.Activity

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devwithadi.todolist.Home.Adapter.Groups_Adapter
import com.devwithadi.todolist.Home.fragments.Tasks
import com.devwithadi.todolist.Modules.*
import com.devwithadi.todolist.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*


class HomeActivity : AppCompatActivity() {
    private var cardView: CardView? = null
     var recyclerView: RecyclerView? = null
    var groups_adapter: Groups_Adapter? = null
    var linearLayoutManager: LinearLayoutManager? = null
    var tasks: List<tasksdata>? = null
    var drawerLayout: DrawerLayout? = null
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null
    var count = 1
    var task :Tasks?=null

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        tasks = ArrayList()
        Group_lists = ArrayList()
        retrofitInstance
        recyclerView = findViewById(R.id.gener)
        cardView = findViewById(R.id.cardview)
        drawerLayout = findViewById(R.id.my_drawer_layout)
        val taskserver = retrofit!!.create(taskserver::class.java)
        val data = taskserver.allPosts
        data.enqueue(object : Callback<List<tasksdata>> {
            override fun onResponse(call: Call<List<tasksdata>>, response: Response<List<tasksdata>>) {
                if (!response.isSuccessful) {
                    Toast.makeText(this@HomeActivity, "Error", Toast.LENGTH_SHORT).show()
                    println("problem")
                }
                var groupid = 0

                val data = response.body()!!
                println("Dasdasd ${data[6].title}")
                try {

                    for (taskss in data) {

                        if (groupid == 0) {
                            groupid = taskss.userId
                        }
                        println(taskss.userId)
                        if (groupid != taskss.userId) {
                            println("O Hello ${(Group_lists as ArrayList<group_list>).size}")
                            (Group_lists as ArrayList<group_list>).add(
                                group_list(
                                    tasks as ArrayList<tasksdata>,
                                    groupid
                                )
                            )
                            groupid = taskss.userId
                            (tasks as ArrayList<tasksdata>).clear()
                        } else {
                            (tasks as ArrayList<tasksdata>).add(
                                tasksdata(
                                    taskss.userId,
                                    taskss.id,
                                    taskss.title,
                                    taskss.body
                                )
                            )
                        }

                    }
                }
                catch (e:Exception){
println("Hello$e")


                }
                attach()
            }

            override fun onFailure(call: Call<List<tasksdata>>, t: Throwable) {
                println("problem2")
            }
        })
        this.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        this.window.statusBarColor = ContextCompat.getColor(this, R.color.dark)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout?.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.custom_actionbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    fun attach() {
        GenerView()
        Group_lists?.let { println(it.size) }
        Task_data = Group_lists!![0].tasksdata
         task = Tasks()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, task!!)
        fragmentTransaction.commit()
        cardView!!.visibility = View.GONE
    }

    private fun GenerView() {

        linearLayoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
        groups_adapter = Group_lists?.let { Groups_Adapter(this@HomeActivity, it) }
        recyclerView?.setLayoutManager(linearLayoutManager)
        recyclerView?.addItemDecoration(horizontal_recycleSpacer(20))
        recyclerView?.setAdapter(groups_adapter)
        recyclerView!!.addOnItemTouchListener(
                RecyclerItemClickListener(this@HomeActivity, recyclerView!!, object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                      Toast.makeText(this@HomeActivity,position.toString(), Toast.LENGTH_SHORT).show();
                        task?.ChangeData(Group_lists!![position].tasksdata)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {

                    }
                })
        )


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            true  // do whatever
        } else super.onOptionsItemSelected(item)
    }

    companion object {
        @JvmField
        var Task_data: List<tasksdata>? = null
        var Group_lists: MutableList<group_list>? = null
        private var retrofit: Retrofit? = null
        private const val URL = "https://jsonplaceholder.typicode.com"
        val retrofitInstance: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                            .baseUrl(URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
                return retrofit
            }
    }
}