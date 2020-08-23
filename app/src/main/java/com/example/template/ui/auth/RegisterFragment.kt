package com.example.template.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.template.base.BaseFragment
import com.example.template.R
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : BaseFragment() {

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
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
        register_register_btn.setOnClickListener { attemptRegister() }
    }

    private fun attemptRegister() {
        viewModel.attemptRegister(
            register_email_et.text.toString(),
            register_username_et.text.toString(),
            register_password_et.text.toString(),
            register_confirm_password_et.text.toString()
        )
    }
}