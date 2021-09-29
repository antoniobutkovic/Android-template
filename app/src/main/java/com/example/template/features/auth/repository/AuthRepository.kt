package com.example.template.features.auth.repository

import com.example.template.common.data.ViewState
import com.example.template.features.auth.network.models.LoginResponse
import com.example.template.features.auth.network.models.RegisterResponse

interface AuthRepository {

    suspend fun attemptLogin(email: String, password: String): ViewState<LoginResponse>

    suspend fun attemptRegister(email: String, username: String, password: String, confirmPassword: String): ViewState<RegisterResponse>

}