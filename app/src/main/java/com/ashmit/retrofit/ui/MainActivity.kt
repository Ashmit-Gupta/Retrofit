package com.ashmit.retrofit.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ashmit.retrofit.R
import com.ashmit.retrofit.data.models.ApiResponse
import com.ashmit.retrofit.di.RetrofitBuilder
import com.ashmit.retrofit.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//todo remove or api files or the api keys from git and the localhost server

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var apiResponse: ApiResponse
        var name: String
        var age: String
        var course: String
        val tv = findViewById<TextView>(R.id.tv)
        val btnSendData = findViewById<Button>(R.id.btnSendData)
        val btnRecData = findViewById<Button>(R.id.btnGetData)
        val btnGetDataRV = findViewById<Button>(R.id.btnGetDataForRv)
        val edtName = findViewById<TextView>(R.id.edtName)
        val edtAge = findViewById<TextView>(R.id.edtAge)
        val edtCourse = findViewById<TextView>(R.id.edtCourse)

        val retrofitBuilder = RetrofitBuilder.getInstance().create(ApiService::class.java)

        btnRecData.setOnClickListener {
            try {
                // Fetching all data from the server
                lifecycleScope.launch(Dispatchers.IO) {
                    val result = retrofitBuilder.getData()
                    if (result.isSuccessful) {
                        apiResponse = result.body()!!
                        withContext(Dispatchers.Main) {
                            tv.text = apiResponse.data.toString()
                        }
                        Log.d("LOG", "status: ${apiResponse.status}")
                        Log.d("LOG", "message: ${apiResponse.message}")
                    } else {
                        Log.d("ERROR", result.toString())
                    }
                }
            } catch (e: Exception) {
                Log.d("ERROR", e.toString())
            }
        }

        btnSendData.setOnClickListener {
            try {
                name = edtName.text.toString()
                age = edtAge.text.toString()
                course = edtCourse.text.toString()
                Log.d("LOG", "Name: $name")

                lifecycleScope.launch(Dispatchers.IO) {
                    val response = retrofitBuilder.sendData(name, age.toInt(), course)
                    if (response.isSuccessful) {
                        Log.d("LOG", "Raw Response: ${response.body()}")
                        withContext(Dispatchers.Main) {
                            edtName.text = ""
                            edtAge.text = ""
                            edtCourse.text = ""
                        }
                        Log.d("LOG", "Data Sent")
                    } else {
                        Log.d("LOG", "Data Not Sent")
                    }
                }
            } catch (e: Exception) {
                Log.d("ERROR", e.message.toString())
                e.printStackTrace()
            }
        }

        btnGetDataRV.setOnClickListener {
            startActivity(Intent(this , RecyclerViewAct::class.java))
        }

    }
}

/*
*
        btnRecData.setOnClickListener {
            try {

                //fetching the whole data from the table
                lifecycleScope.launch (Dispatchers.IO){
                    val result = retrofitBuilder.getData()
                    if(result.isSuccessful){
                        customData = result.body()!!
                        withContext(Dispatchers.Main){
                            tv.text = customData.data.toString()
                        }
                        Log.d("LOG", "status : ${customData.status}")
                        Log.d("LOG", "status : ${customData.message}")
                    }else{
                        Log.d("ERROR" , result.toString())
                    }
                }

                //without parameter query getDataById
                /*
                GlobalScope.launch(Dispatchers.IO) {
                val result = retrofitBuilder.getDataById()
                if(result.isSuccessful){
                dataById = result.body()!!
                Log.d("LOG", "status : ${dataById.status}")
                Log.d("LOG", "status : ${dataById.message}")
                Log.d("LOG", "status : ${dataById.data}")

                }
                }
                */

                /*
                parametrized query getDataById
                GlobalScope.launch(Dispatchers.IO) {
                val result = retrofitBuilder.getDataById(3)
                if (result.isSuccessful) {
                dataById = result.body()!!
                Log.d("LOG", "status : ${dataById.status}")
                Log.d("LOG", "status : ${dataById.message}")
                Log.d("LOG", "status : ${dataById.data}")
                withContext(Dispatchers.Main) {
                tv.text =
                "ID: ${dataById.data.id}\nName: ${dataById.data.name}\nAge: ${dataById.data.age}\nCourse: ${dataById.data.course}"
                }
                }
                }
                */


            } catch (e: Exception) {
                Log.d("ERROR", e.toString())
                Log.d("ERROR", e.message.toString())
                Log.d("ERROR", e.printStackTrace().toString())
            }
        }
        /*

        btnSendData.setOnClickListener {
        try{
        name = edtName.text.toString()
        age = edtAge.text.toString()
        course = edtCourse.text.toString()
        lifecycleScope.launch(Dispatchers.IO) {
        val sentData = CustomAPIForSendingItem(age, course,name)
        val response = retrofitBuilder.sendData(sentData.name,sentData.age,sentData.course)
        Log.d("LOG" , "Raw Response : $response")
        if (response.status == "200") {
        Log.d("LOG", "Data Sent")
        } else {
        Log.d("LOG", "Data Not Sent")
        }
        }

        }catch (e:Exception){
        Log.d("ERROR" , e.message.toString())
        e.printStackTrace()
        }
        }
        */

        btnSendData.setOnClickListener {
            try {
                name = edtName.text.toString()
                age = edtAge.text.toString()
                course = edtCourse.text.toString()
                Log.d("Log", "Name : $name")

                lifecycleScope.launch(Dispatchers.IO) {
                    val response = retrofitBuilder.sendData(name , age.toInt(), course)
                    if (response.isSuccessful) {
                        Log.d("LOG", "Raw Response: ${response.body()}")
                        withContext(Dispatchers.Main){
                            edtName.text = ""
                            edtAge.text = ""
                            edtCourse.text = ""
                        }
                        Log.d("LOG", "Data Sent")
                    } else {
                        Log.d("LOG", "${response.errorBody()}")
                        Log.d("LOG", "Data Not Sent")
                    }
                }
            } catch (e: Exception) {
                Log.d("ERROR", e.message.toString())
                e.printStackTrace()
            }
        }
*/