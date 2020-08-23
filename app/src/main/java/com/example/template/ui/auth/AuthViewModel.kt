package com.example.template.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.template.repository.AuthRepository
import com.example.template.base.BaseViewModel
import com.example.template.models.LoginResponse
import com.example.template.models.RegisterResponse
import com.example.template.utils.DataState
import com.example.template.utils.Error
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel(){

    private val _login = MutableLiveData<DataState<LoginResponse>>()
    val login: LiveData<DataState<LoginResponse>> = _login

    private val _register = MutableLiveData<DataState<RegisterResponse>>()
    val register: LiveData<DataState<RegisterResponse>> = _register

    // Need to move repeated logic to the BaseViewModel
    fun attemptLogin(email: String, password: String){
        _login.value = DataState.loading()
        viewModelScope.launch(IO) {
            try {
                val response = authRepository.attemptLogin(email, password)
                withContext(Main) {
                    _login.value = response
                }
            }catch (e: Exception){
                withContext(Main){
                    _login.value = DataState.error(Error(e.localizedMessage))
                }
            }
        }
    }

    fun attemptRegister(email: String, username: String, password: String, confirmPassword: String){
        _register.value = DataState.loading()
        viewModelScope.launch(IO) {
            try {
                val response = authRepository.attemptRegister(email, username, password, confirmPassword)
                withContext(Main) {
                    _register.value = response
                }
            }catch (e: Exception){
                withContext(Main){
                    _register.value = DataState.error(Error(e.localizedMessage))
                }
            }
        }
    }
}