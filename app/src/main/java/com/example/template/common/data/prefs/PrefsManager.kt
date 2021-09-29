package com.example.template.common.data.prefs

import android.content.Context
import android.preference.PreferenceManager
import javax.inject.Inject

class PrefsManager @Inject constructor(val context: Context) {

    companion object {
        val AUTH_TOKEN = "auth_token"
    }

    fun storeStringField(key: String, value: String?) {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getStringField(key: String, defaultValue: String? = null): String? {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)
        return settings.getString(key, defaultValue)
    }

}