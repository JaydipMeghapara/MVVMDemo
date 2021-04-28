package com.jaydip.mvvmdemo.api

import com.jaydip.mvvmdemo.api.request.AccessTokenRequest
import com.jaydip.mvvmdemo.api.response.ProfileDetailResponse
import com.jaydip.mvvmdemo.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @Headers("Content-Type: application/json")
    @POST("user/access-token")
    suspend fun getUserRefreshToken(@Body string: String): Response<ProfileDetailResponse>

    @Headers("Content-Type: application/json")
    @POST("user/access-token")
    suspend fun getUserAccessToken(
        @Body requestBody: AccessTokenRequest
    ): Response<ResponseBody>

    /*`Global Access*/

    @GET("{SuffixURL}")
    suspend fun getMethodWithQuery(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,

        @QueryMap(encoded = true) params: Map<String, String>
    ): Response<ResponseBody>

    @GET("{SuffixURL}")
    suspend fun getMethodWithQueryWithToken(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @Header("access-token") authHeader: String,
        @QueryMap(encoded = true) params: Map<String, String>
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("{SuffixURL}")
    suspend fun postMethod(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @FieldMap(encoded = true) params: Map<String, String>
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("{SuffixURL}")
    suspend fun postMethodWithToken(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @Header("access-token") authHeader: String,
        @FieldMap(encoded = true) params: Map<String, String>
    ): Response<ResponseBody>


    @Headers("Content-Type: application/json")
    @POST("{SuffixURL}")
    suspend fun postBodyMethod(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @Body postData: RequestBody
    ): Response<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("{SuffixURL}")
    suspend fun postBodyMethodWithToken(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @Header("access-token") authHeader: String,
        @Body postData: RequestBody
    ): Response<ResponseBody>

    @Multipart
    @POST("{SuffixURL}")
    suspend fun postMultiPartMethod(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @PartMap params: Map<String, RequestBody>,
        @Part image: MultipartBody.Part
    ): Response<ResponseBody>

    @Multipart
    @POST("{SuffixURL}")
    suspend fun postMultiPartMethodWithToken(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @Header("access-token") authHeader: String,
        @PartMap params: Map<String, RequestBody>,
        @Part image: MultipartBody.Part
    ): Response<ResponseBody>


    @PUT("{SuffixURL}")
    suspend fun putMethod(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @QueryMap(encoded = true) params: Map<String, String>
    ): Response<ResponseBody>

    @PUT("{SuffixURL}")
    suspend fun putMethodWithToken(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @Header("access-token") authHeader: String,
        @QueryMap(encoded = true) params: Map<String, String>
    ): Response<ResponseBody>


    @Headers("Content-Type: application/json")
    @PUT("{SuffixURL}")
    suspend fun putBodyMethod(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @Body type: RequestBody
    ): Response<ResponseBody>

    @Headers("Content-Type: application/json")
    @PUT("{SuffixURL}")
    suspend fun putBodyMethodWithToken(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @Header("access-token") authHeader: String,
        @Body type: RequestBody
    ): Response<ResponseBody>


    @DELETE("{SuffixURL}")
    suspend fun deleteMethod(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @QueryMap(encoded = true) params: Map<String, String>
    ): Response<ResponseBody>

    @DELETE("{SuffixURL}")
    suspend fun deleteMethodWithToken(
        @Path(value = "SuffixURL", encoded = true) SuffixURL: String,
        @Header("access-token") authHeader: String,
        @QueryMap(encoded = true) params: Map<String, String>
    ): Response<ResponseBody>
}