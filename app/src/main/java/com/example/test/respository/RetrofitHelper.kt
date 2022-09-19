package com.example.test.respository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private var retrofit: Retrofit? = null
    private val baseUrl= "https://reqres.in/api/"

    fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit =
                Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        return retrofit!!
    }
}