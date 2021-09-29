package com.example.template.utils.helpers

data class Error(val errorMessage: String = "",
                 val errorCode: Int = ResponseCodes.UNDEFINED.code,
                 val fieldsErrorType: InputFieldsErrorType = InputFieldsErrorType.NONE)

enum class ResponseCodes(val code: Int) {
    UNDEFINED(0),
    UNAUTHORIZED(401)
}

enum class InputFieldsErrorType {
    NONE,
    POPULATE_ALL_FIELDS,
    EMAIL_NOT_VALID,
    INCORRECT_PASSWORD,
    PASSWORD_TOO_SHORT,
    PASSWORDS_DOES_NOT_MATCH,
}