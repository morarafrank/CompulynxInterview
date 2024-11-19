package com.morarafrank.compulynxinterview.utils

import android.content.Context
import android.content.SharedPreferences

object CompulynxAndroidInterviewSharedPrefs {

    private lateinit var sharedPrefs: SharedPreferences
    fun initSharedPrefs(context: Context) {
        sharedPrefs = context.getSharedPreferences(
            Constants.Preferences.SHARED_PREF_NAME, Context.MODE_PRIVATE
        )
    }

    fun clearInventorySharedPrefs() {
        val editor = sharedPrefs.edit()
        editor.clear()
        editor.apply()
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        val editor = sharedPrefs.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    fun getIsLoggedIn(): Boolean {
        return sharedPrefs.getBoolean("isLoggedIn", false)
    }

    fun saveCustomerId(customerID: String) {
        val editor = sharedPrefs.edit()
        editor.putString("customerID", customerID)
        editor.apply()
    }

    fun getCustomerId(): String {
        return if (sharedPrefs.contains("customerID")) {
            sharedPrefs.getString("customerID", "")!!
        }else{
            ""
        }
    }

    fun saveCustomerAccount(customerAccount: String) {
        val editor = sharedPrefs.edit()
        editor.putString("customerAccount", customerAccount)
        editor.apply()
    }

    fun getCustomerAccount(): String {
        return if (sharedPrefs.contains("customerAccount")) {
            sharedPrefs.getString("customerAccount", "")!!
        }else{
            ""
        }
    }
}