package com.example.navia.data.remote.services

import com.example.kosassignment.data.modal.UserDataResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APICallBack {
    @GET("users")
    fun getUsers(@Query("page") page:Int):Call<UserDataResponse>
}