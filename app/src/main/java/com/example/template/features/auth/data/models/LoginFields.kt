package com.example.template.features.auth.data.models

import android.util.Patterns
import com.example.template.utils.MINIMUM_PASSWORD_LENGTH
import com.example.template.utils.helpers.InputFieldsErrorType

data class LoginFields(
    var email: String? = null,
    var password: String? = null
) {

    fun getFieldsState(): InputFieldsErrorType {

        if (email.isNullOrEmpty()
            || password.isNullOrEmpty()
        ) {
            return InputFieldsErrorType.POPULATE_ALL_FIELDS
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