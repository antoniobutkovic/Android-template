package com.example.template.api

import com.example.template.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiAuthService {

    @POST("api/account/login")
    @FormUrlEncoded
    fun login(@Field("username") email: String, @Field("password") password: String): Call<LoginResponse>
}