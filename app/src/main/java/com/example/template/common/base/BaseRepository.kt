package com.example.template.common.base

import com.example.template.BuildConfig
import com.example.template.common.data.ViewState
import retrofit2.Call
import com.example.template.utils.helpers.Error
import com.example.template.utils.helpers.InputFieldsErrorType


abstract class BaseRepository {

    suspend fun <ResponseType> fetchData(
        apiCall: suspend () -> Call<ResponseType>,
        saveLocal: (() -> Unit)? = null,
        getLocal: (() -> ResponseType)? = null
    ): ViewState<ResponseType> =
        try {
            val response = apiCall().execute()
            val body = response.body()
            if (saveLocal != null && body != null) {
                saveLocal()
            }
            when (response.code()) {
                400 -> ViewState.error(
                    Error(
                        response.message(),
                        400
                    )
                )
                401 -> ViewState.error(
                    Error(
                        response.message(),
                        401,
                        InputFieldsErrorType.INCORRECT_PASSWORD
                    )
                )
                else -> ViewState.success(body)

            }

        } catch (error: Throwable) {
            if (BuildConfig.DEBUG) {
                error.printStackTrace()
            }
            if (getLocal != null) {
                ViewState.error(Error(error.localizedMessage), getLocal?.invoke())
            }else {
                ViewState.error(Error(error.localizedMessage))
            }

        }
}