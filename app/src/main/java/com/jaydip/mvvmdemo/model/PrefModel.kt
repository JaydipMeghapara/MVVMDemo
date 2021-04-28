package com.jaydip.mvvmdemo.model

import com.chibatching.kotpref.KotprefModel

object PrefModel : KotprefModel() {
    var isUserLoggedIn by booleanPref(false)
    var deviceToken by stringPref("")
    var authToken by stringPref("")
    var deviceLastAddress by stringPref("")
    var deviceLastLatitude by stringPref("")
    var deviceLastLongitude by stringPref("")

    //logged in user data
    var userId by intPref(0)
    var roleId by intPref(0)
    var isActive by intPref(0)
    var userMobile by stringPref("")
    var userName by stringPref("")
    var roleName by stringPref("")
    var userEmail by stringPref("")
    var accessToken by stringPref("")
    var region by stringPref("")
    var workStatus by intPref(0)
    var attndStatus by intPref(0)

    var isAttendanceStarted by booleanPref(false)
}
