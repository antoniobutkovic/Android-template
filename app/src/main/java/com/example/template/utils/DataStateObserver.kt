package com.example.template.utils

import androidx.lifecycle.Observer
import com.example.template.base.BaseView

class DataStateObserver<T>(private val view: BaseView, private val onSuccess: (data: T?) -> Unit): Observer<DataState<T>> {

    override fun onChanged(data: DataState<T>?) {
        view.showProgressCircle(data?.status == Status.LOADING)

        if (data?.status == Status.ERROR) {
            data.error?.errorMessage?.let { view.showError(it) }

            when (data.error?.errorCode) {
                ResponseCodes.UNAUTHORIZED.code -> view.onLogout()
            }
        } else if (data?.status == Status.SUCCESS) {
            onSuccess(data.data)
        }
    }
}