package com.example.template.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.template.repository.AuthRepository
import com.example.template.base.BaseViewModel
import com.example.template.models.LoginResponse
import com.example.template.utils.DataState
import com.example.template.utils.Error
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel(){

    private val _dataState = MutableLiveData<DataState<LoginResponse>>()
    val dataState: LiveData<DataState<LoginResponse>> = _dataState

    // Need to move repeated logic to the BaseViewModel
    fun attemptLogin(email: String, password: String){
        _dataState.value = DataState.loading()
        viewModelScope.launch(IO) {
            try {
                val response = authRepository.attemptLogin(email, password)
                withContext(Main) {
                    _dataState.value = response
                }
            }catch (e: Exception){
                withContext(Main){
                    _dataState.value = DataState.error(Error(e.localizedMessage))
                }
            }
        }
    }
}