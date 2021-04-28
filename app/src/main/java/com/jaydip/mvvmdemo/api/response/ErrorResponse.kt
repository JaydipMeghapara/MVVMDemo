package com.jaydip.mvvmdemo.api.response

data class ErrorResponse(
    var responseCode: Int = 0,
    var responseMessage: String = "",
    var trace: MutableList<Trace> = mutableListOf()
) {
    data class Trace(
        var field: String = "",
        var message: String = ""
    )
}