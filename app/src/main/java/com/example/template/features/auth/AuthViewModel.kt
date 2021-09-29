package com.example.template.features.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.template.features.auth.repository.AuthRepository
import com.example.template.common.base.BaseViewModel
import com.example.template.common.data.UIComponentType
import com.example.template.common.data.ViewState
import com.example.template.features.auth.network.models.LoginResponse
import com.example.template.features.auth.network.models.RegisterResponse
import com.example.template.utils.helpers.Error
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val repository: AuthRepository): BaseViewModel(){

    private val _login = MutableLiveData<ViewState<LoginResponse>>()
    val login: LiveData<ViewState<LoginResponse>> = _login

    private val _register = MutableLiveData<ViewState<RegisterResponse>>()
    val register: LiveData<ViewState<RegisterResponse>> = _register

    fun attemptLogin(email: String, password: String){
        _login.value = ViewState.loading()
        doBackgroundCall(
            onBackground = {
                repository.attemptLogin(email, password)
            },
            onResult = { response ->
                _login.value = response?.copy(type = UIComponentType.FIELD)
            },
            onException = { e ->
                _login.value = ViewState.error(Error(e.localizedMessage))
            }
        )
    }

    fun attemptRegister(email: String, username: String, password: String, confirmPassword: String){
        _register.value = ViewState.loading()
        doBackgroundCall(
            onBackground = {
                repository.attemptRegister(email, username, password, confirmPassword)
            },
            onResult = { response ->
                _register.value = response?.copy(type = UIComponentType.FIELD)
            },
            onException = { e ->
                _register.value = ViewState.error(Error(e.localizedMessage))
            }
        )
    }
}