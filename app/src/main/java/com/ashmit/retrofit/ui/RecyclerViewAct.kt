package com.ashmit.retrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashmit.retrofit.R
import com.ashmit.retrofit.data.models.User
import com.ashmit.retrofit.data.models.UserItem
import com.ashmit.retrofit.data.network.ApiService
import com.ashmit.retrofit.di.RetrofitBuilder
import com.ashmit.retrofit.ui.adapter.RecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecyclerViewAct : AppCompatActivity() {
    private var userList : User? = null
    private lateinit var myAdapter : RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_v)
        val rView : RecyclerView = findViewById(R.id.recyclerView)
        rView.layoutManager = LinearLayoutManager(this)

        val userListEmpty = User().apply {
            add(UserItem("unable to fetch data", -1, "unable to fetch data", -1))
        }

        myAdapter = RecyclerViewAdapter(userListEmpty)
        rView.adapter = myAdapter

        lifecycleScope.launch (Dispatchers.IO){
            Log.d("log ", "chalne wala h getDataFromAPI")
            getDataFromAPI()
            Log.d("log ", "running the getDataFromAPI")
            Log.d("log " , "The userList : $userList")
        }


    }
    private suspend fun getDataFromAPI() {
        try{
            val retrofitBuilder = RetrofitBuilder.getInstance().create(ApiService::class.java)

                val response = retrofitBuilder.getPost()
                Log.d("log ,Status" , response.code().toString())
                if(response.isSuccessful){
                    userList = response.body()!!

                }else{
                    userList = User().apply {
                        add(UserItem("unable to fetch data", -1, "unable to fetch data", -1))
                    }

                    Log.d("log , FAILED" , "unable to get Response")
                    Log.d("log , FAILED" , response.errorBody().toString())
                }
            // Update adapter data on the main thread
            withContext(Dispatchers.Main) {
                myAdapter.updateData(userList!!)
            }

        }catch(e:Exception){
            Log.d("log , ERROR" , e.message.toString())
            e.printStackTrace()
        }
    }

}