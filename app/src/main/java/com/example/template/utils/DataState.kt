package com.example.template.utils

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class DataState<out T>(val status: Status?, val data: T?, val error: Error?) {
    companion object {
        fun <T> loading(data: T? = null): DataState<T> {
            return DataState(Status.LOADING, data, null)
        }

        fun <T> success(data: T?): DataState<T> {
            return DataState(Status.SUCCESS, data, null)
        }

        fun <T> error (error: Error, data: T? = null): DataState<T> {
            return DataState(Status.ERROR, data, error)
        }
    }
}