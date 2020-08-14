package com.example.template.base


import com.example.template.BuildConfig
import com.example.template.utils.DataState
import retrofit2.Call
import com.example.template.utils.Error


abstract class BaseRepository {

    suspend fun <ResponseType> doApiCall(
        apiCall: suspend () -> Call<ResponseType>,
        saveLocal: (() -> Unit)? = null,
        getLocal: (() -> ResponseType)? = null
    ): DataState<ResponseType> =
        try {
            val response = apiCall().execute()
            val body = response.body()
            if (saveLocal != null && body != null) {
                // save to local database if lambda is set
                saveLocal()
            }
            if (response.code() == 401) {
                DataState.error(Error(response.message(), 401))
            } else {
                DataState.success(body)
            }

        } catch (error: Throwable) {
            if (BuildConfig.DEBUG) {
                error.printStackTrace()
            }
            if (getLocal != null) {
                // If there is an error getting data (eg. no internet connection) return cached data
                DataState.error(Error(error.localizedMessage), getLocal?.invoke())
            }else {
                DataState.error(Error(error.localizedMessage))
            }

        }
}