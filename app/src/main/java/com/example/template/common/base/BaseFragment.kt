package com.example.template.common.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.template.common.di.viewmodel.ViewModelProviderFactory
import com.example.template.common.session.SessionManager
import com.example.template.utils.DialogUtil
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseFragment: DaggerFragment(), BaseView {

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var loadingDialog: Dialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = DialogUtil.createLoaderDialog(requireActivity())
    }

    override fun showProgress(show: Boolean) {
        if (show) loadingDialog.show() else loadingDialog.hide()
    }

    override fun showToastError(resId: Int) {
        Toast.makeText(requireActivity(), getString(resId), Toast.LENGTH_SHORT).show()
    }

    override fun showToastError(error: String) {
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
    }

    override fun showDialogError(resId: Int) {
    }

    override fun hideKeyboard(view: View) {
    }

    override fun onLogout() {
        sessionManager.logout()
    }

    override fun onDestroy() {
        super.onDestroy()
        loadingDialog.dismiss()
    }
}