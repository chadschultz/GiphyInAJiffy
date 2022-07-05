package com.example.giphyinajiffy

import android.annotation.SuppressLint
import android.util.Log
import timber.log.Timber

/**
 * For signed release builds, only log at the INFO level or above.
 */
class TimberReleaseTree(private val defaultTag: String) : Timber.Tree() {
    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority >= Log.INFO
    }

    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val specificTag = if (tag.isNullOrEmpty()) defaultTag else tag

        when (priority) {
            Log.VERBOSE -> Log.v(specificTag, message, t)
            Log.DEBUG -> Log.d(specificTag, message, t)
            Log.INFO -> Log.i(specificTag, message, t)
            Log.WARN -> Log.w(specificTag, message, t)
            Log.ERROR -> Log.e(specificTag, message, t)
            else -> Log.wtf(specificTag, message, t)
        }
    }
}
