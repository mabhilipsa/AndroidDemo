package com.target.androidcasestudy.network

import com.target.androidcasestudy.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("deals")
    fun getItemList(): Call<ResponseModel>

}