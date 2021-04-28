package com.jaydip.mvvmdemo.api

import com.jaydip.mvvmdemo.api.request.AccessTokenRequest
import com.jaydip.mvvmdemo.api.response.ProfileDetailResponse
import com.jaydip.mvvmdemo.model.User
import okhttp3.ResponseBody
import retrofit2.Response


interface ApiHelper {
    suspend fun getUsers(): Response<List<User>>

    suspend fun getUserRefreshTokenApi(
        refreshToken: String
    ): Response<ProfileDetailResponse>

    suspend fun getUserAccessTokenApi(
        refreshToken: AccessTokenRequest
    ): Response<ResponseBody>

    /* Global API Access */
    suspend fun getMethodQueryApi(
        strURL: String,
        authHeader: String,
        isAuthenticationRequired: Boolean,
        requestHashMap: HashMap<String, String>
    ): Response<ResponseBody>

    suspend fun postMethodApi(
        strURL: String,
        authHeader: String,
        isAuthenticationRequired: Boolean,
        requestHashMap: HashMap<String, String>
    ): Response<ResponseBody>

    @JvmSuppressWildcards
    suspend fun postBodyMethodApi(
        strURL: String,
        requestType: String,
        authHeader: String,
        isAuthenticationRequired: Boolean,
        requestParameter: String
    ): Response<ResponseBody>

//    suspend fun postMultiPartMethodApi(
//        strURL: String, requestHashMap: HashMap<String, String>
//    ): Response<Any>

    suspend fun putMethodApi(
        strURL: String,
        authHeader: String,
        isAuthenticationRequired: Boolean,
        requestHashMap: HashMap<String, String>
    ): Response<ResponseBody>

    suspend fun putBodyMethodApi(
        strURL: String,
        authHeader: String,
        isAuthenticationRequired: Boolean,
        requestParameter: String
    ): Response<ResponseBody>

    suspend fun deleteMethodApi(
        strURL: String,
        authHeader: String,
        isAuthenticationRequired: Boolean,
        requestHashMap: HashMap<String, String>
    ): Response<ResponseBody>

}