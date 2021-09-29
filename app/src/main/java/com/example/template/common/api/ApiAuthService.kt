package com.example.template.common.api

import com.example.template.features.auth.network.models.LoginResponse
import com.example.template.features.auth.network.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiAuthService {

    @POST("api/account/login")
    @FormUrlEncoded
    fun login(
        @Field("username") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @POST("api/account/register")
    @FormUrlEncoded
    fun register(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("password2") confirmPassword: String
    ): Call<RegisterResponse>
}