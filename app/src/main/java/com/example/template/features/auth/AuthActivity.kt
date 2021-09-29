package com.example.template.features.auth

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.common.base.BaseActivity
import com.example.template.R
import com.example.template.features.main.MainActivity
import javax.inject.Inject

class AuthActivity : BaseActivity() {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        subscribeObservers()
        checkIfUserIsAlreadyLoggedIn()
    }

    private fun subscribeObservers() {
        sessionManager.cachedToken.observe(this, Observer { authToken ->
            authToken?.let{
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
