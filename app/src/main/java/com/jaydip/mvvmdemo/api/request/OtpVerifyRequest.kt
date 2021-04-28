package com.jaydip.mvvmdemo.api.request

import com.google.gson.annotations.SerializedName


data class OtpVerifyRequest(

    @SerializedName("mobile")
    var mobile: String = "",

    @SerializedName("otp")
    var otp: Int = 0,
    @SerializedName("otherinfo")
    var otherinfo: String = "",
    @SerializedName("versioninfo")
    var versioninfo: String = "",
    @SerializedName("envinfo")
    var envinfo: String = "",
    @SerializedName("fcm")
    var fcm: String = ""


    )
