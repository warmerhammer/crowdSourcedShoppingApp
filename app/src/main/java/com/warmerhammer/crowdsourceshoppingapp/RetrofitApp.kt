package com.warmerhammer.crowdsourceshoppingapp

import android.app.Application
import retrofit2.Retrofit




class RetrofitApp : Application() {
    companion object{
        private lateinit var instance:RetrofitApp
        fun getInstance() :RetrofitApp = instance
    }

    private var retrofit:Retrofit? = null
    override fun onCreate() {
        super.onCreate()
        instance = this

        val retrofit = Retrofit.Builder()
            .baseUrl("https://shoppingapp-d56022f156a4.herokuapp.com/")
            .build()
    }

    fun getApiService() = retrofit!!.create(APIServices::class.java)
}