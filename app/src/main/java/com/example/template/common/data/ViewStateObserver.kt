package com.example.template.common.data

import androidx.lifecycle.Observer
import com.example.template.common.base.BaseView
import com.example.template.utils.helpers.ResponseCodes

class ViewStateObserver<T>(private val view: BaseView, private val onReturn: (state: ViewState<T>?) -> Unit): Observer<ViewState<T>> {

    override fun onChanged(state: ViewState<T>?) {
        view.showProgress(state?.status == Status.LOADING)
        if (state?.status == Status.ERROR) {

            if (state.error?.errorCode == ResponseCodes.UNAUTHORIZED.code) {
                view.onLogout()
            }

            when(state.type){
                UIComponentType.TOAST -> view.showToastError(state.error!!.errorMessage)
                UIComponentType.DIALOG -> view.showDialogError(0)
                UIComponentType.FIELD -> onReturn(state)
                UIComponentType.NONE -> return
            }

        } else if (state?.status == Status.SUCCESS) {
            onReturn(state)
        }
    }
}