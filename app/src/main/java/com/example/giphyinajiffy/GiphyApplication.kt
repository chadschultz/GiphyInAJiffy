package com.example.giphyinajiffy

import androidx.multidex.MultiDexApplication
import timber.log.Timber

class GiphyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(TimberReleaseTree(applicationContext.getString(R.string.app_name)))
        }
    }

}
