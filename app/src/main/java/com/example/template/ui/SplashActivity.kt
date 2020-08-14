package com.example.template.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.template.ui.auth.AuthActivity

class SplashActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToAuthActivity()
    }

    private fun navigateToAuthActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

}