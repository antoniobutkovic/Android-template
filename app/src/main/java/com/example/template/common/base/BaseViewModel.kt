package com.example.template.common.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

abstract class BaseViewModel: ViewModel() {

    fun <T: Any> doBackgroundCall(onBackground: suspend () -> T?, onResult: ((T?) -> Unit)? = null, onException: ((Exception) -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val data = viewModelScope.async(Dispatchers.IO) {
                    return@async onBackground()
                }
                withContext(Dispatchers.Main){
                    onResult?.invoke(data.await())
                }
            }catch (e: Exception) {
                withContext(Dispatchers.Main){
                    onException?.invoke(e)
                }
            }
        }
    }

}