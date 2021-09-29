package com.example.template.common.base

import android.os.Bundle
import com.example.template.common.session.SessionManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}