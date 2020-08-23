package com.example.template.session

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.template.utils.PrefsManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(private val prefsManager: PrefsManager) {

    private val _cachedToken = MutableLiveData<String?>()
    val cachedToken: LiveData<String?> get() = _cachedToken

    fun login(newValue: String){
        setValue(newValue)
    }

    fun logout(){
        setValue(null)
    }

    fun checkIfUserIsAlreadyLoggedIn(){
        val token = prefsManager.getStringField(PrefsManager.AUTH_TOKEN)
        if (token != null){
            _cachedToken.value = token
        }
    }

    fun setValue(newValue: String?) {
        if (_cachedToken.value != newValue) {
            _cachedToken.value = newValue
            prefsManager.storeStringField(PrefsManager.AUTH_TOKEN, newValue)
        }
    }
}
