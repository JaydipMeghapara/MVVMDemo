package com.jaydip.mvvmdemo.model

import com.chibatching.kotpref.KotprefModel
import com.jaydip.mvvmdemo.util.AppConstant.PREF_QA_SERVER

object PrefGlobal : KotprefModel() {
    var isRequestingLocationUpdates by booleanPref(false)
    var PREF_API_SERVER by stringPref(PREF_QA_SERVER)
}