package com.example.template.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.template.common.base.BaseFragment
import com.example.template.R
import com.example.template.common.data.ViewStateObserver
import com.example.template.utils.helpers.InputFieldsErrorType
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment: BaseFragment() {

    private val viewModel by viewModels<AuthViewModel> { providerFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.register.observe(viewLifecycleOwner,
            ViewStateObserver(this) { data ->
                data?.data?.token?.let {
                    sessionManager.login(it)
                }

                data?.error?.fieldsErrorType.let { type ->
                    handleFieldErrors(type)
                }
            })
    }

    private fun handleFieldErrors(type: InputFieldsErrorType?) {
        when(type){
            InputFieldsErrorType.POPULATE_ALL_FIELDS -> {
                register_email_til.error = getString(R.string.auth_populate_fields_error)
                register_username_til.error = getString(R.string.auth_populate_fields_error)
                register_password_til.error = getString(R.string.auth_populate_fields_error)
                register_confirm_password_til.error = getString(R.string.auth_populate_fields_error)
            }
            InputFieldsErrorType.INCORRECT_PASSWORD,
            InputFieldsErrorType.PASSWORD_TOO_SHORT-> {
                register_password_til.error = getString(R.string.auth_password_incorrect_error)
            }
            InputFieldsErrorType.EMAIL_NOT_VALID -> {
                register_email_til.error = getString(R.string.auth_email_incorrect_error)
            }
            InputFieldsErrorType.PASSWORDS_DOES_NOT_MATCH -> {
                register_confirm_password_til.error = getString(R.string.auth_password_no_match_error)
            }
        }
    }

    private fun setClickListeners() {
        register_register_btn.setOnClickListener { attemptRegister() }
        register_sign_in_tv.setOnClickListener { findNavController().popBackStack() }

        register_email_et.addTextChangedListener { resetErrorMessages() }
        register_username_et.addTextChangedListener { resetErrorMessages() }
        register_password_et.addTextChangedListener { resetErrorMessages() }
        register_confirm_password_et.addTextChangedListener { resetErrorMessages() }
    }

    private fun resetErrorMessages(){
        register_email_til.error = null
        register_username_til.error = null
        register_password_til.error = null
        register_confirm_password_til.error = null
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