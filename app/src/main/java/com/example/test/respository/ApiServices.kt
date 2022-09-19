package com.example.test.respository

import com.example.test.ui.model.UserResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("users")
    suspend fun getUserList(@Query("page") page: Int): Response<UserResponseModel>

}