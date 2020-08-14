package com.example.template.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.template.base.BaseFragment
import com.example.template.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: BaseFragment() {

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setClickListeners()
        subscribeObservers()
    }

    private fun subscribeObservers() {

    }

    private fun setClickListeners() {
        login_sign_up_tv.setOnClickListener { navigateToRegisterFragment() }
        login_sign_in_btn.setOnClickListener { attemptLogin() }
    }

    private fun attemptLogin() {
        viewModel.attemptLogin(login_email_et.text.toString(), login_password_et.text.toString())
    }

    private fun navigateToRegisterFragment() {
        navController.navigate(R.id.action_loginFragment_to_registerFragment)
    }
}