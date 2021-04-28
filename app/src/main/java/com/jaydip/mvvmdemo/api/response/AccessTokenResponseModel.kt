package com.jaydip.mvvmdemo.api.response

data class AccessTokenResponseModel(
    var responseCode: Int = 0,
    var responseMessage: String = "",
    var data: Data? = Data()
) {
    data class Data(
        var access_token: String = "",
        var refresh_token: String = "",
        var token_type: String = "",
        var expires_in: Int = 0
    )
}