package com.morarafrank.compulynxinterview.utils

import android.content.Context
import android.widget.Toast

object UiUtils {

    // General Toast
    fun showToast(message: String, context: Context){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}