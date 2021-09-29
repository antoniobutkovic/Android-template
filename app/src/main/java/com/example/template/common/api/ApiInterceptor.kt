package com.example.template.common.api

import com.example.template.common.data.prefs.PrefsManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiInterceptor @Inject constructor(private val prefsManager: PrefsManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        val sessionToken = prefsManager.getStringField(PrefsManager.AUTH_TOKEN)

        if (sessionToken != null) {
            requestBuilder.addHeader("Authorization", "Token $sessionToken")
        }

        return chain.proceed(requestBuilder.build())
    }
}