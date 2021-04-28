package com.jaydip.mvvmdemo.api.response

import com.google.gson.annotations.SerializedName

data class OrderModel(
    @SerializedName("ord_id")
    var ord_id: Int = 0,
    @SerializedName("ord_type")
    var ord_type: String = "",
    @SerializedName("to_addr.addr_1")
    var to_addr_addr_1: String = "",
    @SerializedName("to_addr.addr_2")
    var to_addr_addr_2: String = "",
    @SerializedName("to_addr.addr_name")
    var to_addr_addr_name: String = "",
    @SerializedName("to_addr.area")
    var to_addr_area: String = "",
    @SerializedName("to_addr.city")
    var to_addr_city: String = "",
    @SerializedName("to_addr.state")
    var to_addr_state: String = "",
    @SerializedName("to_addr.pincode")
    var to_addr_pincode: String = "",
    @SerializedName("to_addr.addr_type")
    var to_addr_addr_type: Int = 0,
    @SerializedName("to_addr.mobile")
    var to_addr_mobile: String = "",
    @SerializedName("from_addr.addr_1")
    var from_addr_addr_1: String = "",
    @SerializedName("from_addr.addr_2")
    var from_addr_addr_2: String = "",
    @SerializedName("from_addr.addr_name")
    var from_addr_addr_name: String = "",
    @SerializedName("from_addr.area")
    var from_addr_area: String = "",
    @SerializedName("from_addr.city")
    var from_addr_city: String = "",
    @SerializedName("from_addr.state")
    var from_addr_state: String = "",
    @SerializedName("from_addr.pincode")
    var from_addr_pincode: String = "",
    @SerializedName("from_addr.addr_type")
    var from_addr_addr_type: Int = 0,
    @SerializedName("from_addr.mobile")
    var from_addr_mobile: String = "",
    @SerializedName("amount")
    var amount: Int = 0,
    @SerializedName("pick_dt")
    var pick_dt: String = "",
    @SerializedName("del_dt")
    var del_dt: String = "",
    @SerializedName("weight")
    var weight: Int = 0
)