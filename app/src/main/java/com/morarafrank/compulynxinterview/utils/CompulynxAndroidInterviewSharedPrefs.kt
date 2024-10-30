package com.morarafrank.compulynxinterview.utils

import android.content.Context
import android.content.SharedPreferences

object CompulynxAndroidInterviewSharedPrefs {

    private lateinit var inventorySharedPrefs: SharedPreferences
    fun initSharedPrefs(context: Context) {
        inventorySharedPrefs = context.getSharedPreferences(
            Constants.Preferences.SHARED_PREF_NAME, Context.MODE_PRIVATE
        )
    }

    fun clearInventorySharedPrefs() {
        val editor = inventorySharedPrefs.edit()
        editor.clear()
        editor.apply()
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        val editor = inventorySharedPrefs.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    fun getIsLoggedIn(): Boolean {
        return inventorySharedPrefs.getBoolean("isLoggedIn", false)
    }
}