package com.example.template.features.auth.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse(

    @SerializedName("response")
    @Expose
    var response: String,

    @SerializedName("error_message")
    @Expose
    var errorMessage: String,

    @SerializedName("token")
    @Expose
    var token: String,

    @SerializedName("pk")
    @Expose
    var pk: Int,

    @SerializedName("email")
    @Expose
    var email: String
)