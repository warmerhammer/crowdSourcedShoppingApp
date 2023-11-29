package com.warmerhammer.crowdsourceshoppingapp.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    fun getRetrofit() = Retrofit.Builder()
        .baseUrl("https://shoppingapp-d56022f156a4.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIServices::class.java)
}