package com.jaydip.mvvmdemo.api.response

import com.google.gson.annotations.SerializedName

data class LogOutResponse(

    @SerializedName("responseCode")
    var responseCode: Int = 0,


    @SerializedName("responseMessage")
    var responseMessage: String = "",

    @SerializedName("trace")
    var trace: MutableList<Trace> = mutableListOf()
) {
    data class Trace(
        var field: String = "",
        var message: String = ""
    )
}