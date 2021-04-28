package com.jaydip.mvvmdemo.api.request

import com.google.gson.annotations.SerializedName


data class OrderListRequest(
    @SerializedName("type")
    var type: String = "",
    @SerializedName("page_no")
    var page_no: Int = 0,
    @SerializedName("per_page")
    var per_page: Int = 10
)
