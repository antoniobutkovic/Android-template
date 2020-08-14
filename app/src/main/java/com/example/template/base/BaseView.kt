package com.example.template.base

import android.view.View
import androidx.annotation.StringRes

interface BaseView {

    fun showProgressCircle(show: Boolean)

    fun showError(errorMessage: String)

    fun showError(stringResourceId: Int)

    fun hideKeyboard(view: View)

    fun onLogout()

}