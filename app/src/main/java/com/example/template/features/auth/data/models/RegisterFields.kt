package com.example.template.features.auth.data.models

import android.util.Patterns
import com.example.template.utils.MINIMUM_PASSWORD_LENGTH
import com.example.template.utils.helpers.InputFieldsErrorType

data class RegisterFields(
    var email: String? = null,
    var username: String? = null,
    var password: String? = null,
    var confirmPassword: String? = null
){
    fun getFieldsState(): InputFieldsErrorType {
        if(email.isNullOrEmpty()
            || username.isNullOrEmpty()
            || password.isNullOrEmpty()
            || confirmPassword.isNullOrEmpty()){
            return InputFieldsErrorType.POPULATE_ALL_FIELDS
        }

        if(!password.equals(confirmPassword)){
            return InputFieldsErrorType.PASSWORDS_DOES_NOT_MATCH
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email!!).matches()){
            return InputFieldsErrorType.EMAIL_NOT_VALID
        }

        if (password!!.length <= MINIMUM_PASSWORD_LENGTH) {
            return InputFieldsErrorType.PASSWORD_TOO_SHORT
        }
        return InputFieldsErrorType.NONE
    }
}