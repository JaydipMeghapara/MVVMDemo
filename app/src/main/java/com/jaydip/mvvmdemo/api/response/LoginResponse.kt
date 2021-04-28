package com.jaydip.mvvmdemo.api.response

import com.google.gson.annotations.SerializedName


data class LoginResponse(

    @SerializedName("status")
    var status: Int = 0,


    @SerializedName("message")
    var message: String = ""

)