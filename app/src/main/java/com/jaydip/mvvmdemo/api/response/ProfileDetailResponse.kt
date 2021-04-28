package com.jaydip.mvvmdemo.api.response

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName

data class ProfileDetailResponse(

    @SerializedName("responseCode")
    var responseCode: Int = 0,


    @SerializedName("responseMessage")
    var responseMessage: String = "",

    @SerializedName("data")
    var data: UserResponseData,

    @SerializedName("trace")
    var trace: MutableList<Trace> = mutableListOf()

) {
    data class UserResponseData(
        @SerializedName("profileData")
        var profileDetails: ProfileResponseData
    ) {
        data class ProfileResponseData(
            @SerializedName("id")
            var userId: Int = 0,

            @SerializedName("firstName")
            var firstName: String = "",

            @SerializedName("lastName")
            var lastName: String = "",

            @SerializedName("mobileNo")
            var mobileNo: String = "",

            @SerializedName("email")
            var email: String = "",

            @SerializedName("dateOfBirth")
            @Nullable
            var dateOfBirth: String = "",

            @SerializedName("gender")
            @Nullable
            var gender: String = "",

            @Nullable
            @SerializedName("path")
            var path: String = "",

            @SerializedName("isMobileVerified")
            var isMobileVerified: Boolean = false,

            @SerializedName("isEmailVerified")
            var isEmailVerified: Boolean = false
        )
    }
    data class Trace(
        var field: String = "",
        var message: String = ""
    )
}