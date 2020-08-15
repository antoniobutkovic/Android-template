package com.example.template.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.base.BaseActivity
import com.example.template.R
import com.example.template.ui.main.MainActivity
import com.example.template.utils.DataStateObserver
import javax.inject.Inject

class AuthActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

        subscribeObservers()
        checkIfUserIsAlreadyLoggedIn()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, DataStateObserver(this) { data ->
            data?.token?.let {
                sessionManager.login(it)
            }
        })
        sessionManager.cachedToken.observe(this, Observer { authToken ->
            authToken.let{
                navigateToMainActivity()
            }
        })
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun checkIfUserIsAlreadyLoggedIn() {
        sessionManager.checkIfUserIsAlreadyLoggedIn()
    }
}
