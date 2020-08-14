package com.example.template.base

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.template.session.SessionManager
import com.example.template.utils.DialogUtil
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity(), BaseView {

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var loadingDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadingDialog = DialogUtil.createLoaderDialog(this)
    }

    override fun showProgressCircle(show: Boolean) {
        if (show) loadingDialog.show() else loadingDialog.hide()
        Log.e("TAG", "show progress: $show")
    }

    override fun showError(errorMessage: String) {
        Log.e("TAG", "error message: $errorMessage")
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showError(stringResourceId: Int) {
        Log.e("TAG", "stringId: $stringResourceId")
    }

    override fun showLoader(show: Boolean) {
        Log.e("TAG", "show loader: $show")
    }

    override fun showLoader(show: Boolean, cancelable: Boolean) {
        Log.e("TAG", "show loader cancelable: $show")
    }

    override fun showShortInfo(info: String) {
        Log.e("TAG", "show show info: $info")
    }

    override fun showShortInfo(stringResourceId: Int) {
        Log.e("TAG", "short info stringId: $stringResourceId")
    }

    override fun showInfoDialog(title: String?, description: String?, buttonText: String?) {
        Log.e("TAG", "show info dialog: $title")
    }

    override fun showInfoDialog(titleResourceId: Int, descriptionResourceId: Int, buttonText: Int) {
        Log.e("TAG", "show info dialog stringId: $titleResourceId")
    }

    override fun hideKeyboard(view: View) {
        Log.e("TAG", "hide keyboard")
    }

    override fun onLogout() {
        sessionManager.logout()
        Log.e("TAG", "on logout")
    }

}