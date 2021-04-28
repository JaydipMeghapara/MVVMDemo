package com.jaydip.mvvmdemo.api.request

import com.google.gson.annotations.SerializedName


data class LoginRequest(

    @SerializedName("mobile")
    var mobile: String = "",

    @SerializedName("envinfo")
    var envinfo: String = ""


    )
