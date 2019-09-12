package com.mvvmdemo.data.prefrences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import java.util.*

private const val KEY_SAVED_AT = "key_saved_at"

class PrefrencesProvider(context: Context) {

    private val appContext = context.applicationContext

    private val prefrence: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    fun saveLastSaveAt(savedAt: String) {

        prefrence.edit().putString(KEY_SAVED_AT,savedAt).apply()
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun getLastSavedAt(): String? {
        return prefrence.getString(KEY_SAVED_AT, null)
    }
}