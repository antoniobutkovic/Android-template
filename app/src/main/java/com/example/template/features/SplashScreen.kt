package com.example.template.features

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.template.features.auth.AuthActivity

class SplashScreen: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToAuthActivity()
    }

    private fun navigateToAuthActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

}