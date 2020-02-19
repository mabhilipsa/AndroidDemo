package com.target.androidcasestudy.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {

    const val BASE_URL="http://target-deals.herokuapp.com/api/"

    fun getApiBuilder(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
}