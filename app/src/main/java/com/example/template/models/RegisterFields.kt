package com.example.template.models


data class RegisterFields(
    var email: String? = null,
    var username: String? = null,
    var password: String? = null,
    var confirmPassword: String? = null
){

    class RegistrationError {
        companion object{

            fun mustFillAllFields(): String{
                return "All fields are required."
            }

            fun passwordsDoNotMatch(): String{
                return "Passwords must match."
            }

            fun none():String{
                return "None"
            }

        }
    }

    fun isValidForRegistration(): String {
        if(email.isNullOrEmpty()
            || username.isNullOrEmpty()
            || password.isNullOrEmpty()
            || confirmPassword.isNullOrEmpty()){
            return RegistrationError.mustFillAllFields()
        }

        if(!password.equals(confirmPassword)){
            return RegistrationError.passwordsDoNotMatch()
        }
        return RegistrationError.none()
    }
}