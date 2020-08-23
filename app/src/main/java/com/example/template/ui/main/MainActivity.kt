package com.example.template.ui.main

import android.content.Intent
import android.os.Bundle
import com.example.template.R
import com.example.template.base.BaseActivity
import com.example.template.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setClickListeners()
    }

    private fun setClickListeners() {
        main_logout_btn.setOnClickListener {
            onLogout()
            navigateToAuthActivity()
        }
    }

    private fun navigateToAuthActivity() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }

}