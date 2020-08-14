package com.example.template.utils

enum class ResponseCodes(val code: Int) {
    UNDEFINED(0),
    UNAUTHORIZED(401)
}

data class Error(val errorMessage: String, val errorCode: Int = ResponseCodes.UNDEFINED.code)