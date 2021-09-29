package com.example.template.features.auth.repository

import com.example.template.common.api.ApiAuthService
import com.example.template.common.base.BaseRepository
import com.example.template.common.data.ViewState
import com.example.template.common.data.db.AppDatabase
import com.example.template.features.auth.data.models.LoginFields
import com.example.template.features.auth.data.models.RegisterFields
import com.example.template.features.auth.network.models.LoginResponse
import com.example.template.features.auth.network.models.RegisterResponse
import com.example.template.utils.helpers.InputFieldsErrorType
import com.example.template.utils.helpers.Error
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: ApiAuthService, private val database: AppDatabase): BaseRepository(), AuthRepository {

    override suspend fun attemptLogin(email: String, password: String): ViewState<LoginResponse> {
        val loginFieldErrors = LoginFields(email, password).getFieldsState()
        return if(loginFieldErrors == InputFieldsErrorType.NONE){
            fetchData(apiCall = {api.login(email, password)})
        } else {
            ViewState.error(Error(fieldsErrorType = loginFieldErrors))
        }
    }

    override suspend fun attemptRegister(email: String, username: String, password: String, confirmPassword: String): ViewState<RegisterResponse> {
        val registerFieldErrors = RegisterFields(email, username, password, confirmPassword).getFieldsState()
        return if (registerFieldErrors == InputFieldsErrorType.NONE){
            fetchData(apiCall = {api.register(email, username, password, confirmPassword)})
        } else {
            ViewState.error(Error(fieldsErrorType = registerFieldErrors))
        }
    }

}