package com.jaydip.mvvmdemo.api

import com.jaydip.mvvmdemo.api.ApiHelper
import com.jaydip.mvvmdemo.api.request.AccessTokenRequest
import com.jaydip.mvvmdemo.api.response.ProfileDetailResponse
import com.jaydip.mvvmdemo.model.PrefModel
import com.jaydip.mvvmdemo.model.User
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Response


class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<List<User>> {
        return apiService.getUsers()
    }

    override suspend fun getUserRefreshTokenApi(
        refreshToken: String
    ): Response<ProfileDetailResponse> {
        return apiService.getUserRefreshToken(refreshToken)
    }

    override suspend fun getUserAccessTokenApi(
        refreshToken: AccessTokenRequest
    ): Response<ResponseBody> {
        return apiService.getUserAccessToken(refreshToken)
    }
    /* Global API Access */

    override suspend fun getMethodQueryApi(
        strURL: String,
        authHeader: String,
        isAuthenticationRequired: Boolean,
        requestHashMap: HashMap<String, String>
    ): Response<ResponseBody> {
        return if (isAuthenticationRequired && PrefModel.isUserLoggedIn) {
            apiService.getMethodWithQueryWithToken(strURL, authHeader, requestHashMap)
        } else {
            apiService.getMethodWithQuery(strURL, requestHashMap)
        }
    }

    override suspend fun postMethodApi(
        strURL: String, authHeader: String,
        isAuthenticationRequired: Boolean, requestHashMap: HashMap<String, String>
    ): Response<ResponseBody> {
        return if (isAuthenticationRequired && PrefModel.isUserLoggedIn) {
            apiService.postMethodWithToken(strURL, authHeader, requestHashMap)
        } else {
            apiService.postMethod(strURL, requestHashMap)
        }
    }

    @JvmSuppressWildcards
    override suspend fun postBodyMethodApi(
        strURL: String, requestType: String, authHeader: String,
        isAuthenticationRequired: Boolean, requestParameter: String
    ): Response<ResponseBody> {
        return if (isAuthenticationRequired && PrefModel.isUserLoggedIn) {
            apiService.postBodyMethodWithToken(
                strURL, authHeader,
                requestParameter.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            )
        } else {
            apiService.postBodyMethod(
                strURL,
                requestParameter.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            )
        }
    }
//    override suspend fun postMultiPartMethodApi(
//        strURL: String, requestHashMap: HashMap<String, String>
//    ): Response<Any> {
//        return apiService.postMultiPartMethod(strURL, requestHashMap)
//    }

    override suspend fun putMethodApi(
        strURL: String, authHeader: String,
        isAuthenticationRequired: Boolean, requestHashMap: HashMap<String, String>
    ): Response<ResponseBody> {
        return if (isAuthenticationRequired && PrefModel.isUserLoggedIn) {
            apiService.putMethodWithToken(strURL, authHeader, requestHashMap)
        } else {
            apiService.putMethod(strURL, requestHashMap)
        }
    }

    override suspend fun putBodyMethodApi(
        strURL: String, authHeader: String,
        isAuthenticationRequired: Boolean, requestParameter: String
    ): Response<ResponseBody> {
        return if (isAuthenticationRequired && PrefModel.isUserLoggedIn) {
            apiService.putBodyMethodWithToken(
                strURL, authHeader,
                requestParameter.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            )
        } else {
            apiService.putBodyMethod(
                strURL,
                requestParameter.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            )
        }
    }

    override suspend fun deleteMethodApi(
        strURL: String, authHeader: String,
        isAuthenticationRequired: Boolean, requestHashMap: HashMap<String, String>
    ): Response<ResponseBody> {
        return if (isAuthenticationRequired && PrefModel.isUserLoggedIn) {
            apiService.deleteMethodWithToken(strURL, authHeader, requestHashMap)
        } else {
            apiService.deleteMethod(strURL, requestHashMap)
        }
    }

}