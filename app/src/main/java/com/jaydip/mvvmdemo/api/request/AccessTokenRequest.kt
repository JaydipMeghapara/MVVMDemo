package com.jaydip.mvvmdemo.api.request

import com.google.gson.annotations.SerializedName


data class AccessTokenRequest(
    @SerializedName("refreshToken")
    var refreshToken: String = ""
)
