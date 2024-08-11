package com.ashmit.retrofit.data.network

import com.ashmit.retrofit.data.models.ApiResponse
import com.ashmit.retrofit.data.models.InsertDataResponse
import com.ashmit.retrofit.data.models.SingleItemResponse
import com.ashmit.retrofit.data.models.User
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService{
    //end points
    @GET("posts")
    suspend fun getPost(): Response<User> // returns the list of all the users or all the data

    //getting the whole data
    @GET("getData.php")
    suspend fun getData(): retrofit2.Response<ApiResponse>

    //getting the data by ID
//    @GET("getDataById.php/?id=3")
//    suspend fun getDataById(): retrofit2.Response<CustomAPIForSingleItem>

    // Getting the data by ID with a dynamic query parameter
    @GET("getDataById.php")
    suspend fun getDataById(@Query("id") id :Int): retrofit2.Response<SingleItemResponse>


    @FormUrlEncoded
    @POST("setData.php")
    suspend fun sendData(@Field ("name") name:String, @Field("age") age:Int, @Field("course") course:String):Response<InsertDataResponse>

}