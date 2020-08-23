package com.example.template.repository

import com.example.template.api.ApiAuthService

import com.example.template.base.BaseRepository
import com.example.template.models.LoginFields
import com.example.template.models.LoginResponse
import com.example.template.models.RegisterFields
import com.example.template.models.RegisterResponse
import com.example.template.persistence.AppDatabase
import com.example.template.utils.DataState
import com.example.template.utils.*
import javax.inject.Inject

class AuthRepository @Inject constructor(private val api: ApiAuthService, private val database: AppDatabase): BaseRepository() {

    suspend fun attemptLogin(email: String, password: String): DataState<LoginResponse> {
        val loginFieldErrors = LoginFields(email, password).isValidForLogin()
        return if(loginFieldErrors == LoginFields.LoginError.none()){
            doApiCall(apiCall = {api.login(email, password)})
        }else {
            DataState.error(Error(loginFieldErrors))
        }
    }

    suspend fun attemptRegister(email: String, username: String, password: String, confirmPassword: String): DataState<RegisterResponse> {
        val registerFieldErrors = RegisterFields(email, username, password, confirmPassword).isValidForRegistration()
        return if (registerFieldErrors == RegisterFields.RegistrationError.none()){
            doApiCall(apiCall = {api.register(email, username, password, confirmPassword)})
        }else {
            DataState.error(Error(registerFieldErrors))
        }
    }

}