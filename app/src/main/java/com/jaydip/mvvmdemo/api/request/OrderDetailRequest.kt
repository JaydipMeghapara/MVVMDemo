package com.jaydip.mvvmdemo.api.request

import com.google.gson.annotations.SerializedName


data class OrderDetailRequest(
    @SerializedName("ord_id")
    var ord_id: Int = 0
)
