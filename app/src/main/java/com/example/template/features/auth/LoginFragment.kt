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
import com.example.template.utils.makeGone
import com.example.template.utils.makeVisible
import com.example.template.utils.onError
import com.example.template.utils.onSuccess
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: BaseFragment() {

    private val viewModel by viewModels<AuthViewModel> { providerFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.login.observe(viewLifecycleOwner,
            ViewStateObserver(this) { state ->
                state?.onSuccess { data ->
                    data?.token?.let {
                        sessionManager.login(it)
                    }
                }

                state?.onError { error ->
                    handleFieldErrors(error?.fieldsErrorType)
                }
            })
    }

    private fun handleFieldErrors(type: InputFieldsErrorType?) {
        when(type){
            InputFieldsErrorType.POPULATE_ALL_FIELDS -> {
                login_email_til.error = getString(R.string.auth_populate_fields_error)
                login_password_til.error = getString(R.string.auth_populate_fields_error)
                login_forgot_password_tv.makeGone()
            }
            InputFieldsErrorType.INCORRECT_PASSWORD,
            InputFieldsErrorType.PASSWORD_TOO_SHORT-> {
                login_password_til.error = getString(R.string.auth_password_incorrect_error)
                login_forgot_password_tv.makeGone()
            }
            InputFieldsErrorType.EMAIL_NOT_VALID -> {
                login_email_til.error = getString(R.string.auth_email_incorrect_error)
            }
        }
    }

    private fun setClickListeners() {
        login_sign_up_tv.setOnClickListener { navigateToRegisterFragment() }
        login_sign_in_btn.setOnClickListener { attemptLogin() }

        login_email_et.addTextChangedListener { resetErrorMessages() }
        login_password_et.addTextChangedListener { resetErrorMessages() }
    }

    private fun resetErrorMessages(){
        login_email_til.error = null
        login_password_til.error = null
        login_forgot_password_tv.makeVisible()
    }

    private fun attemptLogin() {
        viewModel.attemptLogin(login_email_et.text.toString(), login_password_et.text.toString())
    }

    private fun navigateToRegisterFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }
}