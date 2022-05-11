package com.mentalmachines.han.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences
    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        const val PREF_FILE_NAME = "mvpstarter_pref_file"
    }

    init {
        sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }
}