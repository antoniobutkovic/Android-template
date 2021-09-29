package com.example.template.common.base

import android.view.View

interface BaseView {

    fun showProgress(show: Boolean)

    fun showToastError(resId: Int)

    fun showToastError(error: String)

    fun showDialogError(resId: Int)

    fun hideKeyboard(view: View)

    fun onLogout()

}