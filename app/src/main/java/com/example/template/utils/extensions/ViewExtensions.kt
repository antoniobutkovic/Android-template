package com.example.template.utils

import android.view.View
import com.example.template.common.data.Status
import com.example.template.common.data.ViewState
import com.example.template.utils.helpers.Error

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

inline fun <reified T> ViewState<T>.onSuccess(callback: (data: T?) -> Unit) {
    if (this.status == Status.SUCCESS) {
        callback(this.data)
    }
}

inline fun <reified T> ViewState<T>.onError(callback: (error: Error?) -> Unit) {
    if (this.status == Status.ERROR) {
        callback(this.error)
    }
}