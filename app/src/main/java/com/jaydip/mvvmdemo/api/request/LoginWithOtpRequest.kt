package com.jaydip.mvvmdemo.api.request

import com.google.gson.annotations.SerializedName


data class LoginWithOtpRequest(

    @SerializedName("deviceToken")
    var deviceToken: String = "",


    @SerializedName("deviceType")
    var deviceType: String = "",

    @SerializedName("type")
    var type: String = "",

    @SerializedName("value")
    var value: String = ""


) {
    constructor() : this("", "", "", "")
}
