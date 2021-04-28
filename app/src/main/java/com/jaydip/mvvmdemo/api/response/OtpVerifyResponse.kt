package com.jaydip.mvvmdemo.api.response

import com.google.gson.annotations.SerializedName


data class OtpVerifyResponse(

    @SerializedName("status")
    var status: Int = 0,

    @SerializedName("message")
    var message: String = "",

    @SerializedName("data")
    var data: Data

) {
    data class Data(
        var user_id: Int = 0,
        var role_id: Int = 0,

        var mobile: String = "",
        var name: String = "",
        var role_name: String = "",
        var token: String = "",
        var region: String = "",
        var email: String = "",
        var is_active: Int = 0,
        var work_status: Int = 0,
        var attnd_status: Int = 0
    )
}
