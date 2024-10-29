package com.morarafrank.compulynxinterview

import android.app.Application
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CompulynxInterviewApp : Application(){

    override fun onCreate() {
        super.onCreate()


//        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())
//        }
    }
}