package com.jaydip.mvvmdemo.util

import com.jaydip.mvvmdemo.BuildConfig
import timber.log.Timber

/**
 * Created by Mayur Solanki on 29/09/20, 4:48 PM.
 */

object AppLogger {
    val TAG: String = BuildConfig.APPLICATION_ID

    fun debug(strMsg: String) {
        Timber.d(strMsg)
    }

    fun error(strMsg: String) {
        Timber.e(strMsg)
    }

    fun info(strMsg: String) {
        Timber.i(strMsg)
    }

    fun verbose(strMsg: String) {
        Timber.v(strMsg)
    }

    fun warning(strMsg: String) {
        Timber.w(strMsg)
    }
}


