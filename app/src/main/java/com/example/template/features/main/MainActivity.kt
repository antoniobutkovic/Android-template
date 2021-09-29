package com.example.template.features.main

import android.content.Intent
import android.os.Bundle
import com.example.template.R
import com.example.template.common.base.BaseActivity
import com.example.template.features.auth.AuthActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setClickListeners()
    }

    private fun setClickListeners() {
        main_logout_btn.setOnClickListener {
            sessionManager.logout()
            navigateToAuthActivity()
        }
    }

    private fun navigateToAuthActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

}