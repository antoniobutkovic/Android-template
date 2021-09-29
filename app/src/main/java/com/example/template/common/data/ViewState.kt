package com.example.template.common.data

import com.example.template.utils.helpers.Error

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

enum class UIComponentType {
    NONE,
    TOAST,
    DIALOG,
    FIELD
}

data class ViewState<out T>(val status: Status?, val data: T?, val error: Error?, val type: UIComponentType) {
    companion object {
        fun <T> loading(data: T? = null): ViewState<T> {
            return ViewState(
                Status.LOADING,
                data,
                null,
                UIComponentType.NONE
            )
        }

        fun <T> success(data: T?): ViewState<T> {
            return ViewState(
                Status.SUCCESS,
                data,
                null,
                UIComponentType.NONE
            )
        }

        fun <T> error (error: Error, data: T? = null): ViewState<T> {
            return ViewState(
                Status.ERROR,
                data,
                error,
                UIComponentType.NONE
            )
        }
    }
}