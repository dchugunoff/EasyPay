package com.prmatch.easypay.utils

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPreferencesHelper(private val context: Context) {

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun checkToken(token: String): Boolean {
        return sharedPreferences.contains(token)
    }

    fun setToken(token: String) {
        sharedPreferences.edit {
            putString(TOKEN, token)
        }
    }

    fun getToken(): String {
        return sharedPreferences.getString(TOKEN, "") ?: ""
    }
}