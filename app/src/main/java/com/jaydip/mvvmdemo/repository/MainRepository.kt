package com.jaydip.mvvmdemo.repository


import android.content.Intent
import android.widget.Toast
import android.widget.Toast.makeText
import com.jaydip.mvvmdemo.MainApplication
import com.jaydip.mvvmdemo.R
import com.jaydip.mvvmdemo.api.ApiHelper
import com.jaydip.mvvmdemo.api.request.AccessTokenRequest
import com.jaydip.mvvmdemo.api.response.AccessTokenResponseModel
import com.jaydip.mvvmdemo.api.response.ErrorResponse
import com.jaydip.mvvmdemo.model.PrefModel
import com.jaydip.mvvmdemo.model.User
import com.jaydip.mvvmdemo.util.*
import com.jaydip.mvvmdemo.util.AppConstant.DELETE
import com.jaydip.mvvmdemo.util.AppConstant.GET
import com.jaydip.mvvmdemo.util.AppConstant.POST
import com.jaydip.mvvmdemo.util.AppConstant.POST_BODY
import com.jaydip.mvvmdemo.util.AppConstant.PUT
import com.jaydip.mvvmdemo.util.AppConstant.PUT_BODY
import com.jaydip.mvvmdemo.util.AppConstant.REQUEST_PARAMS_POST_BODY_REQUEST_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response


class MainRepository(private val apiHelper: ApiHelper, private val networkHelper: NetworkHelper) {


//    fun getFeeds(): LiveData<Resource<List<User>?>> = object : NetworkBoundResource<List<User>, List<User>>(appExecutors) {
//        override fun saveCallResult(item: List<User>) {
//            feedDao.insertAll(item)
//        }
//        override fun shouldFetch(data: List<User>?): Boolean = data == null || data.isEmpty()
//
//        override fun loadFromDb(): LiveData<List<User>> = userDao.getAllUser()
//
//        override fun createCall(): LiveData<ApiResponse<List<Feed>>> = feedService.getFeeds()
//    }.asLiveData()

    suspend fun refreshUsersFromApi(): Flow<Resource<List<User>>> {
        return flow {
            val newsSource = apiHelper.getUsers()
            if (newsSource.isSuccessful) {
                emit(Resource.success(newsSource.body()))
                newsSource.body()!!.forEach {
//                    userDao.insertUser(it)
                }
            } else {
                emit(Resource.error(newsSource.message(), null))
            }
        }
    }

//    suspend fun getVerticalList(
//        filter: String,
//        page: String,
//        size: String
//    ): Flow<Resource<VerticalListResponseModel>> {
//        return flow {
//            val newsSource = apiHelper.getAllVerticalList(filter, page, size)
//            if (newsSource.isSuccessful) {
//                emit(Resource.success(newsSource.body()))
//
//            } else {
//                emit(Resource.error(newsSource.message(), null))
//            }
//        }
//    }

//    suspend fun doLoginWithOtp(loginWithOtpRequest: LoginWithOtpRequest): Resource<LoginResponse> {
//
//        val newsSource = apiHelper.loginWithOtp(loginWithOtpRequest)
//        if (newsSource.isSuccessful) {
//            return (Resource.success(newsSource.body()))
//        } else {
////            val jsonObj = JSONObject(newsSource.errorBody()!!.charStream().readText())
////            return (Resource.error(jsonObj.getString("responseMessage"), null))
//            return (Resource.error(newsSource.message(), null))
//        }
//    }

//    suspend fun doLoginWithResendOtp(loginWithOtpRequest: LoginWithOtpRequest): Resource<LoginResponse> {
//
//        val newsSource = apiHelper.loginWithResendOtp(loginWithOtpRequest)
//        if (newsSource.isSuccessful) {
//            return (Resource.success(newsSource.body()))
//
//        } else {
//            return (Resource.error(newsSource.message(), null))
//        }
//
//    }


//    suspend fun getCategoryList(
//        longitude: String,
//        latitude: String,
//        verticalCode: String
//    ): Flow<Resource<CategoryListResponse>> {
//        return flow {
//            val newsSource = apiHelper.getCategoryList(longitude, latitude, verticalCode)
//            if (newsSource.isSuccessful) {
//                emit(Resource.success(newsSource.body()))
//
//            } else {
//                emit(Resource.error(newsSource.message(), null))
//            }
//        }
//    }


//    suspend fun getOutletList(outletListingRequest: OutletListingRequest): Resource<StoreListResponseModel> {
//
//        val newsSource = apiHelper.getOutletList(outletListingRequest)
//        if (newsSource.isSuccessful) {
//            return Resource.success(newsSource.body())
//        } else {
//            return Resource.error(newsSource.message(), null)
//        }
//    }

//    suspend fun doLoginEmailPwdWithApi(loginRequest: LoginRequest): Resource<LoginResponse> {
//
//        val newsSource = apiHelper.doLoginWithEmailPwd(loginRequest)
//
//        if (newsSource.isSuccessful) {
//            return Resource.success(newsSource.body())
//        } else {
//            return Resource.error(newsSource.message(), null)
//        }
//
//    }


//    suspend fun doLogOutApi(logOutAccessToken: String): Resource<LogOutResponse> {
//
//        val newsSource = apiHelper.doLogOut(logOutAccessToken)
//
//        if (newsSource.isSuccessful) {
//            return Resource.success(newsSource.body())
//        } else {
//            return Resource.error(newsSource.message(), null)
//        }
//
//    }


//    suspend fun doSendEmailVerificatioApi(
//        email: String,
//        userId: Int
//    ): Flow<Resource<LoginResponse>> {
//        return flow {
//            val newsSource = apiHelper.sendEmilVerification(email, userId)
//            if (newsSource.isSuccessful) {
//                emit(Resource.success(newsSource.body()))
//
//            } else {
//                emit(Resource.error(newsSource.message(), null))
//            }
//        }
//    }


//    suspend fun getUserProfileDetailsApi(
//        userType: String,
//        userId: String
//    ): Flow<Resource<ProfileDetailResponse>> {
//        return flow {
//            val newsSource = apiHelper.getUserProfileDetailsApi(userType, userId)
//            if (newsSource.isSuccessful) {
//                emit(Resource.success(newsSource.body()))
//
//            } else {
//                emit(Resource.error(newsSource.message(), null))
//            }
//        }
//    }


//    suspend fun doChangePassword(changePwdRequest: ChangePwdRequest): Resource<ChangePwdResponse> {
//
//        val newsSource = apiHelper.doChangePwd(changePwdRequest)
//
//        if (newsSource.isSuccessful) {
//            return Resource.success(newsSource.body())
//        } else {
//            return Resource.error(newsSource.message(), null)
//        }
//
//    }

//    suspend fun getOutletDetails(outletDetailsRequest: OutletDetailsRequest): Resource<OutletDetailsResponse> {
//        val newsSource = apiHelper.getOutletDetails(outletDetailsRequest)
//        if (newsSource.isSuccessful) {
//            return Resource.success(newsSource.body())
//        } else {
//            return Resource.error(newsSource.message(), null)
//        }
//    }


//    suspend fun getOutletServiceList(outletServiceListRequest: OutletServiceListRequest): Resource<OutletServiceListResponse> {
//        val newsSource = apiHelper.getOutletServiceList(outletServiceListRequest)
//        if (newsSource.isSuccessful) {
//            return Resource.success(newsSource.body())
//        } else {
//            return Resource.error(newsSource.message(), null)
//        }
//    }

    //    suspend fun apiCallingMethod(
//        callMethod: Int,
//        strURL: String, requestHashMap: HashMap<String, String>
//    ): Flow<Resource<String>> {
//        return flow {
//            var newsSource: Response<ResponseBody>? = null
//            when (callMethod) {
//                GET -> {
//                    newsSource = apiHelper.getMethodQueryApi(strURL, requestHashMap)
//                }
////                POST -> {
////                    newsSource = apiHelper.postMethodApi(strURL, requestHashMap)
////                }
////                POST_BODY -> {
////                    newsSource = apiHelper.postBodyMethodApi(strURL, requestHashMap)
////                }
////                PUT -> {
////                    newsSource = apiHelper.putMethodApi(strURL, requestHashMap)
////                }
////                PUT_BODY -> {
////                    newsSource = apiHelper.putBodyMethodApi(strURL, requestHashMap)
////                }
////                DELETE -> {
////                    newsSource = apiHelper.deleteMethodApi(strURL, requestHashMap)
////                }
//            }
//           if (newsSource!!.isSuccessful) {
//               val stringResponse = newsSource.body()?.string()
//               AppLogger.debug("stringResponse$stringResponse")
//                Resource.success(newsSource.body().toString())
//            } else {
//                Resource.error(newsSource.message(), null)
//            }
//        }
//    }

//    suspend fun getMethodApi(
//        callMethod: @AppConstant.CallMethod Int, SuffixURL: String,
//        params: Map<String, String>
//    ): Flow<HashMap<String, String>> {
//        var strAccessToken: String =
//            "Bearer " + readPrefencesDataString(PrefsKeys.PREF_LOGIN_TOKEN, "")!!
//        //var strAccessToken: String = "Bearer ZKTurRDVMZcTRto6BeDzEP30xy1hsBtD"
//        val responseMap = HashMap<String, String>()
//        return flow {
//            var newsSource: Response<ResponseBody>? = null
//            if (callMethod == AppConstant.GET) {
//                newsSource = apiHelper.getMethod(SuffixURL, strAccessToken, params)
//            } else if (callMethod == AppConstant.POST) {
//                newsSource = apiHelper.postMethod(SuffixURL, strAccessToken, params)
//            }
//
//            if (newsSource != null && newsSource.isSuccessful) {
//                responseMap[AppConstant.API_URL] = newsSource.raw().request.url.toString()
//                responseMap[AppConstant.API_RESPONSE_CODE] = newsSource.code().toString()
//                responseMap[AppConstant.API_RESPONSE_BODY] = newsSource.body()!!.string()!!
//                emit(responseMap)
//            } else {
//                try {
//                    val gson = Gson()
//                    var responseModel =
//                        gson?.fromJson(
//                            newsSource!!.errorBody()!!.charStream(),
//                            com.dogfriendlynz.model.Response::class.java
//                        )
//                    responseMap[AppConstant.API_URL] = SuffixURL
//                    responseMap[AppConstant.API_RESPONSE_CODE] = 1001.toString()
//                    responseMap[AppConstant.API_RESPONSE_BODY] = responseModel.message
//                } catch (e: Exception) {
//                    responseMap[AppConstant.API_URL] = SuffixURL
//                    responseMap[AppConstant.API_RESPONSE_CODE] = 1001.toString()
//                    responseMap[AppConstant.API_RESPONSE_BODY] = "Something went wrong"
//                    e.printStackTrace()
//                }
//                emit(responseMap)
//            }
//        }
//    }
    suspend fun apiCallingMethod(
    callMethod: @AppConstant.CallMethod Int, strURL: String, isAuthenticationRequired: Boolean,
    requestHashMap: HashMap<String, String>
    ): Flow<Resource<HashMap<String, String>>> {
        val strAccessToken: String =
            PrefModel.accessToken
        val responseMap = HashMap<String, String>()
        var newsSource: Response<ResponseBody>? = null
        return flow {
        when (callMethod) {
            GET -> {
                newsSource = apiHelper.getMethodQueryApi(
                    strURL,
                    strAccessToken,
                    isAuthenticationRequired,
                    requestHashMap
                )
            }
            POST -> {
                newsSource = apiHelper.postMethodApi(
                    strURL, strAccessToken,
                    isAuthenticationRequired, requestHashMap
                )
            }
            POST_BODY -> {
                val strJson: String =
                    requestHashMap[REQUEST_PARAMS_POST_BODY_REQUEST_KEY].toString()
                newsSource = apiHelper.postBodyMethodApi(
                    strURL, "application/json", strAccessToken,
                    isAuthenticationRequired, strJson
                )
            }
            PUT -> {
                newsSource = apiHelper.putMethodApi(
                    strURL, strAccessToken,
                    isAuthenticationRequired, requestHashMap
                )
            }
            PUT_BODY -> {
                val strJson: String =
                    requestHashMap[REQUEST_PARAMS_POST_BODY_REQUEST_KEY].toString()
                newsSource = apiHelper.putBodyMethodApi(
                    strURL, strAccessToken,
                    isAuthenticationRequired, strJson
                )
            }
            DELETE -> {
                newsSource = apiHelper.deleteMethodApi(
                    strURL, strAccessToken,
                    isAuthenticationRequired, requestHashMap
                )
            }
        }

        if (newsSource != null && newsSource!!.code() == AppConstant.ResponseStatusCode.STATUS_200) {
            responseMap[AppConstant.API_URL] = newsSource!!.raw().request.url.toString()
            responseMap[AppConstant.API_RESPONSE_CODE] = newsSource!!.code().toString()
            responseMap[AppConstant.API_RESPONSE_BODY] = newsSource!!.body()!!.string()
//            return Resource.success(responseMap)
            emit(Resource.success(responseMap))
        } else if (newsSource != null && newsSource!!.code() == AppConstant.ResponseStatusCode.STATUS_201) {
            responseMap[AppConstant.API_URL] = newsSource!!.raw().request.url.toString()
            responseMap[AppConstant.API_RESPONSE_CODE] = newsSource!!.code().toString()
            responseMap[AppConstant.API_RESPONSE_BODY] = newsSource!!.errorBody()!!.string()
            emit(Resource.success(responseMap))
        } else if (newsSource != null && newsSource!!.code() == AppConstant.ResponseStatusCode.STATUS_400) {
            emit( try {
                responseMap[AppConstant.API_URL] = newsSource!!.raw().request.url.toString()
                responseMap[AppConstant.API_RESPONSE_CODE] = newsSource!!.code().toString()
                responseMap[AppConstant.API_RESPONSE_BODY] = newsSource!!.errorBody()!!.string()
                val apiErrorResponseModel = convertJsonToClass(
                    responseMap[AppConstant.API_RESPONSE_BODY].toString(),
                    ErrorResponse::class.java
                )
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    makeText(
                        MainApplication.applicationContext(),
                        apiErrorResponseModel.responseMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Resource.error(apiErrorResponseModel.responseMessage, responseMap)

            } catch (e: Exception) {
                e.printStackTrace()
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    makeText(
                        MainApplication.applicationContext(),
                        MainApplication.applicationContext()
                            .getString(R.string.str_something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Resource.error(
                    MainApplication.applicationContext()
                        .getString(R.string.str_something_went_wrong), null
                )
            })
        } else if (newsSource!!.code() == AppConstant.ResponseStatusCode.STATUS_401) {
            /*Unauthorized */
            emit( try {
                responseMap[AppConstant.API_URL] = newsSource!!.raw().request.url.toString()
                responseMap[AppConstant.API_RESPONSE_CODE] = newsSource!!.code().toString()
                responseMap[AppConstant.API_RESPONSE_BODY] = newsSource!!.errorBody()!!.string()
                val apiErrorResponseModel = convertJsonToClass(
                    responseMap[AppConstant.API_RESPONSE_BODY].toString(),
                    ErrorResponse::class.java
                )
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    makeText(
                        MainApplication.applicationContext(),
                        apiErrorResponseModel.responseMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Resource.error(apiErrorResponseModel.responseMessage, responseMap)
            } catch (e: Exception) {
                e.printStackTrace()
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    makeText(
                        MainApplication.applicationContext(),
                        MainApplication.applicationContext()
                            .getString(R.string.str_something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Resource.error(
                    MainApplication.applicationContext()
                        .getString(R.string.str_something_went_wrong), null
                )
            })
        } else if (newsSource != null && newsSource!!.code() == AppConstant.ResponseStatusCode.STATUS_404) {
            emit( try {
                responseMap[AppConstant.API_URL] = newsSource!!.raw().request.url.toString()
                responseMap[AppConstant.API_RESPONSE_CODE] = newsSource!!.code().toString()
                responseMap[AppConstant.API_RESPONSE_BODY] = newsSource!!.errorBody()!!.string()
                val apiErrorResponseModel = convertJsonToClass(
                    responseMap[AppConstant.API_RESPONSE_BODY].toString(),
                    ErrorResponse::class.java
                )
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    makeText(
                        MainApplication.applicationContext(),
                        apiErrorResponseModel.responseMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Resource.error(apiErrorResponseModel.responseMessage, responseMap)
            } catch (e: Exception) {
                e.printStackTrace()
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    makeText(
                        MainApplication.applicationContext(),
                        MainApplication.applicationContext()
                            .getString(R.string.str_something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Resource.error(
                    MainApplication.applicationContext()
                        .getString(R.string.str_something_went_wrong), null
                )
            })
        } else if (newsSource != null && newsSource!!.code() == AppConstant.ResponseStatusCode.STATUS_422) {
            emit( try {
                responseMap[AppConstant.API_URL] = newsSource!!.raw().request.url.toString()
                responseMap[AppConstant.API_RESPONSE_CODE] = newsSource!!.code().toString()
                responseMap[AppConstant.API_RESPONSE_BODY] = newsSource!!.errorBody()!!.string()
                val apiErrorResponseModel = convertJsonToClass(
                    responseMap[AppConstant.API_RESPONSE_BODY].toString(),
                    ErrorResponse::class.java
                )
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    makeText(
                        MainApplication.applicationContext(),
                        apiErrorResponseModel.responseMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Resource.error(apiErrorResponseModel.responseMessage, responseMap)

            } catch (e: Exception) {
                e.printStackTrace()
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    makeText(
                        MainApplication.applicationContext(),
                        MainApplication.applicationContext()
                            .getString(R.string.str_something_went_wrong),
                        Toast.LENGTH_SHORT
                    ).show()
                }
//                Resource.errorResponse(null)
                Resource.error(
                    MainApplication.applicationContext()
                        .getString(R.string.str_something_went_wrong), null
                )
            })
        } else if (newsSource != null && newsSource!!.code() == AppConstant.ResponseStatusCode.STATUS_500) {
            responseMap[AppConstant.API_URL] = newsSource!!.raw().request.url.toString()
            responseMap[AppConstant.API_RESPONSE_CODE] = newsSource!!.code().toString()
            responseMap[AppConstant.API_RESPONSE_BODY] = newsSource!!.errorBody()!!.string()
            emit( try {
                val apiErrorResponseModel = convertJsonToClass(
                    responseMap[AppConstant.API_RESPONSE_BODY].toString(),
                    ErrorResponse::class.java
                )
                if (!apiErrorResponseModel.responseMessage.isBlank()) {
                    CoroutineScope(Job() + Dispatchers.Main).launch {
                        makeText(
                            MainApplication.applicationContext(),
                            apiErrorResponseModel.responseMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Resource.error( apiErrorResponseModel.responseMessage, responseMap)
                } else {
                    CoroutineScope(Job() + Dispatchers.Main).launch {
                        makeText(
                            MainApplication.applicationContext(),
                            MainApplication.applicationContext()
                                .getString(R.string.internal_server_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Resource.error(
                        MainApplication.applicationContext()
                            .getString(R.string.internal_server_error),
                        null
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    makeText(
                        MainApplication.applicationContext(),
                        MainApplication.applicationContext()
                            .getString(R.string.internal_server_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Resource.error(
                    MainApplication.applicationContext()
                        .getString(R.string.str_something_went_wrong), null
                )
            })
        } else {
            emit( try {
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    makeText(
                        MainApplication.applicationContext(),
                        MainApplication.applicationContext()
                            .getString(R.string.internal_server_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                Resource.error(
                    MainApplication.applicationContext()
                        .getString(R.string.internal_server_error),
                    null
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Resource.error(
                    MainApplication.applicationContext()
                        .getString(R.string.str_something_went_wrong), null
                )
            })
        }
//            if (newsSource != null && newsSource.isSuccessful) {
//                responseMap[AppConstant.API_URL] = newsSource.raw().request.url.toString()
//                responseMap[AppConstant.API_RESPONSE_CODE] = newsSource.code().toString()
//                responseMap[AppConstant.API_RESPONSE_BODY] = newsSource.body()!!.string()!!
//                emit(responseMap)
//            } else {
//                try {
//                    val gson = Gson()
//                    val responseModel =
//                        gson?.fromJson(
//                            newsSource!!.errorBody()!!.charStream(),
//                            com.dogfriendlynz.model.Response::class.java
//                        )
//                    responseMap[AppConstant.API_URL] = SuffixURL
//                    responseMap[AppConstant.API_RESPONSE_CODE] = 1001.toString()
//                    responseMap[AppConstant.API_RESPONSE_BODY] = responseModel.message
//                } catch (e: Exception) {
//                    responseMap[AppConstant.API_URL] = SuffixURL
//                    responseMap[AppConstant.API_RESPONSE_CODE] = 1001.toString()
//                    responseMap[AppConstant.API_RESPONSE_BODY] = "Something went wrong"
//                    e.printStackTrace()
//                }
//                emit(responseMap)
//            }
//        }
        }
    }

//    private suspend fun getAccessTokenApi(
//        callMethod: @AppConstant.CallMethod Int,
//        strURL: String,
//        strAccessToken: String,
//        isAuthenticationRequired: Boolean,
//        requestHashMap: HashMap<String, String>
//    ): Flow<Resource<HashMap<String, String>>> {
//        val accessTokenRequest = AccessTokenRequest()
//        accessTokenRequest.refreshToken = PrefModel.accessToken
//        val accessTokenOld = AppConstant.ACCESS_TOKEN_TYPE + " " + PrefModel.accessToken
//        return flow {
//            val newsSource = apiHelper.getUserAccessTokenApi(accessTokenRequest)
//            if (newsSource.code() == AppConstant.ResponseStatusCode.STATUS_200) {
//                val refreshTokenResponseMap = HashMap<String, String>()
//                refreshTokenResponseMap[AppConstant.API_URL] =
//                    newsSource.raw().request.url.toString()
//                refreshTokenResponseMap[AppConstant.API_RESPONSE_CODE] =
//                    newsSource.code().toString()
//                refreshTokenResponseMap[AppConstant.API_RESPONSE_BODY] =
//                    newsSource.body()!!.string()
//                val strResponseBody: String =
//                    refreshTokenResponseMap[AppConstant.API_RESPONSE_BODY]!!
//                val apiResponseModel = convertJsonToClass(
//                    strResponseBody,
//                    AccessTokenResponseModel::class.java
//                )
//                if (requestHashMap.isNotEmpty()) {
//                    val strOldResponseBody: String =
//                        requestHashMap[AppConstant.REQUEST_PARAMS_POST_BODY_REQUEST_KEY].toString()
//                    val strOldAccessToken: String =
//                        requestHashMap["accessToken"].toString()
//                    AppLogger.debug("ACCESS-TOKEN-OLD=$strOldResponseBody")
//                    AppLogger.debug("ACCESS-TOKEN-OLD1=$strOldAccessToken")
//                    AppLogger.debug("REQUEST PARAMETER=$requestHashMap")
//                    if (strOldResponseBody != null && strOldResponseBody != "" && strOldResponseBody != "null") {// Handle for change password api request parameter
//                        val strNewResponseBody =
//                            strOldResponseBody.replace(accessTokenOld.toRegex()) {
//                                AppConstant.ACCESS_TOKEN_TYPE + " " + apiResponseModel.data!!.access_token
//                            }
//                        AppLogger.debug("ACCESS-TOKEN=$strNewResponseBody")
//                        if (strNewResponseBody != null && strNewResponseBody != "" && strNewResponseBody != "null") {
//                            requestHashMap[AppConstant.REQUEST_PARAMS_POST_BODY_REQUEST_KEY] =
//                                strNewResponseBody
//                        }
//                    } else if (strOldAccessToken != null && strOldAccessToken != "" && strOldAccessToken != "null") { // Handle for logout api parameter
//                        val strNewResponseBody =
//                            strOldAccessToken.replace(PrefModel.accessToken.toRegex()) {
//                                apiResponseModel.data!!.access_token
//                            }
//                        AppLogger.debug("ACCESS-TOKEN1=$strNewResponseBody")
//                        if (strNewResponseBody != null && strNewResponseBody != "" && strNewResponseBody != "null") {
//                            requestHashMap["accessToken"] =
//                                strNewResponseBody
//                        }
//                    }
//                }
//                AppLogger.debug("REQUEST PARAMETER1=$requestHashMap")
//                PrefModel.accessToken = apiResponseModel.data!!.access_token
//                val apiResponseAuthorized = apiCallingMethod(
//                    callMethod,
//                    strURL,
//                    isAuthenticationRequired,
//                    requestHashMap
//                )
//                apiResponseAuthorized.collect {
//                    emit(it)
//                }
//            } else {
//                clearUserDataToPreference()
////                val intent = Intent(MainApplication.applicationContext(), LoginActivity::class.java)
////                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
////                (MainApplication.applicationContext()).startActivity(intent)
//                CoroutineScope(Job() + Dispatchers.Main).launch {
//                    makeText(
//                        MainApplication.applicationContext(),
//                        MainApplication.applicationContext().getString(R.string.str_logout_success),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                emit( Resource.error(
//                    "",
//                    null
//                ))
//            }
//        }
//    }

//    suspend fun apiCallingMethod(
//        callMethod: @AppConstant.CallMethod Int, strURL: String, isAuthenticationRequired: Boolean,
//        requestHashMap: HashMap<String, String>
//    ): Resource<HashMap<String, String>> {
//        val strAccessToken: String =
//            "bearer " + PrefModel.accessToken
//        val responseMap = HashMap<String, String>()
//        var newsSource: Response<ResponseBody>? = null
////        return flow {
//            when (callMethod) {
//                GET -> {
//                    newsSource = apiHelper.getMethodQueryApi(
//                        strURL,
//                        strAccessToken,
//                        isAuthenticationRequired,
//                        requestHashMap
//                    )
//                }
//                POST -> {
//                    newsSource = apiHelper.postMethodApi(
//                        strURL, strAccessToken,
//                        isAuthenticationRequired, requestHashMap
//                    )
//                }
//                POST_BODY -> {
//                    val strJson: String =
//                        requestHashMap[REQUEST_PARAMS_POST_BODY_REQUEST_KEY].toString()
//                    newsSource = apiHelper.postBodyMethodApi(
//                        strURL, "application/json", strAccessToken,
//                        isAuthenticationRequired, strJson
//                    )
//                }
//                PUT -> {
//                    newsSource = apiHelper.putMethodApi(
//                        strURL, strAccessToken,
//                        isAuthenticationRequired, requestHashMap
//                    )
//                }
//                PUT_BODY -> {
//                    val strJson: String =
//                        requestHashMap[REQUEST_PARAMS_POST_BODY_REQUEST_KEY].toString()
//                    newsSource = apiHelper.putBodyMethodApi(
//                        strURL, strAccessToken,
//                        isAuthenticationRequired, strJson
//                    )
//                }
//                DELETE -> {
//                    newsSource = apiHelper.deleteMethodApi(
//                        strURL, strAccessToken,
//                        isAuthenticationRequired, requestHashMap
//                    )
//                }
//            }
//
//            if (newsSource != null && newsSource!!.code() == AppConstant.ResponseStatusCode.STATUS_200) {
//                responseMap[AppConstant.API_URL] = newsSource!!.raw().request.url.toString()
//                responseMap[AppConstant.API_RESPONSE_CODE] = newsSource!!.code().toString()
//                responseMap[AppConstant.API_RESPONSE_BODY] = newsSource!!.body()!!.string()
//                return Resource.success(responseMap)
//
//            } else if (newsSource != null && newsSource!!.code() == AppConstant.ResponseStatusCode.STATUS_201) {
//                responseMap[AppConstant.API_URL] = newsSource!!.raw().request.url.toString()
//                responseMap[AppConstant.API_RESPONSE_CODE] = newsSource!!.code().toString()
//                responseMap[AppConstant.API_RESPONSE_BODY] = newsSource!!.body()!!.string()
//                return Resource.success(responseMap)
//            } else if (newsSource!! != null && newsSource!!.code() == AppConstant.ResponseStatusCode.STATUS_400) {
//                return try {
//                    responseMap[AppConstant.API_URL] = newsSource!!.raw().request.url.toString()
//                    responseMap[AppConstant.API_RESPONSE_CODE] = newsSource!!.code().toString()
//                    responseMap[AppConstant.API_RESPONSE_BODY] = newsSource!!.errorBody()!!.string()
//                    val apiErrorResponseModel = convertJsonToClass(
//                        responseMap[AppConstant.API_RESPONSE_BODY].toString(),
//                        ErrorResponse::class.java
//                    )
//                    if (apiErrorResponseModel.trace.isNotEmpty()) {
//                        CoroutineScope(Job() + Dispatchers.Main).launch {
//                            makeText(
//                                MainApplication.applicationContext(),
//                                apiErrorResponseModel.trace[0].message,
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                        Resource.error(apiErrorResponseModel.trace[0].message, responseMap)
//                    } else {
//                        CoroutineScope(Job() + Dispatchers.Main).launch {
//                            makeText(
//                                MainApplication.applicationContext(),
//                                apiErrorResponseModel.responseMessage,
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                        Resource.error(apiErrorResponseModel.responseMessage, responseMap)
//                    }
//
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    Resource.error(
//                        MainApplication.applicationContext()
//                            .getString(R.string.str_something_went_wrong), null
//                    )
//                }
//            } else if (newsSource!! != null && newsSource!!.code() == AppConstant.ResponseStatusCode.STATUS_401) {
//                /*Unauthorized */
//                return getAccessTokenApi(
//                    callMethod,
//                    strURL,
//                    strAccessToken,
//                    isAuthenticationRequired,
//                    requestHashMap
//                )
//            } else if (newsSource != null && newsSource.code() == AppConstant.ResponseStatusCode.STATUS_404) {
//                return try {
//                    responseMap[AppConstant.API_URL] = newsSource.raw().request.url.toString()
//                    responseMap[AppConstant.API_RESPONSE_CODE] = newsSource.code().toString()
//                    responseMap[AppConstant.API_RESPONSE_BODY] = newsSource.errorBody()!!.string()
//                    val apiErrorResponseModel = convertJsonToClass(
//                        responseMap[AppConstant.API_RESPONSE_BODY].toString(),
//                        ErrorResponse::class.java
//                    )
//                    CoroutineScope(Job() + Dispatchers.Main).launch {
//                        makeText(
//                            MainApplication.applicationContext(),
//                            apiErrorResponseModel.responseMessage,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    Resource.error(apiErrorResponseModel.responseMessage, responseMap)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    Resource.error(
//                        MainApplication.applicationContext()
//                            .getString(R.string.str_something_went_wrong), null
//                    )
//                }
//            } else if (newsSource != null && newsSource.code() == AppConstant.ResponseStatusCode.STATUS_422) {
//                return try {
//                    responseMap[AppConstant.API_URL] = newsSource.raw().request.url.toString()
//                    responseMap[AppConstant.API_RESPONSE_CODE] = newsSource.code().toString()
//                    responseMap[AppConstant.API_RESPONSE_BODY] = newsSource.errorBody()!!.string()
//                    val apiErrorResponseModel = convertJsonToClass(
//                        responseMap[AppConstant.API_RESPONSE_BODY].toString(),
//                        ErrorResponse::class.java
//                    )
//                    CoroutineScope(Job() + Dispatchers.Main).launch {
//                        makeText(
//                            MainApplication.applicationContext(),
//                            apiErrorResponseModel.responseMessage,
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    Resource.error(apiErrorResponseModel.responseMessage, responseMap)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    Resource.error(
//                        MainApplication.applicationContext()
//                            .getString(R.string.str_something_went_wrong), null
//                    )
//                }
//            } else if (newsSource != null && newsSource.code() == AppConstant.ResponseStatusCode.STATUS_500) {
//                responseMap[AppConstant.API_URL] = newsSource.raw().request.url.toString()
//                responseMap[AppConstant.API_RESPONSE_CODE] = newsSource.code().toString()
//                responseMap[AppConstant.API_RESPONSE_BODY] = newsSource.errorBody()!!.string()
//                return try {
//                    CoroutineScope(Job() + Dispatchers.Main).launch {
//                        makeText(
//                            MainApplication.applicationContext(),
//                            MainApplication.applicationContext()
//                                .getString(R.string.internal_server_error),
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    Resource.error(
//                        MainApplication.applicationContext()
//                            .getString(R.string.internal_server_error),
//                        null
//                    )
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    Resource.error(
//                        MainApplication.applicationContext()
//                            .getString(R.string.str_something_went_wrong), null
//                    )
//                }
//            } else {
//                return try {
//                    CoroutineScope(Job() + Dispatchers.Main).launch {
//                        makeText(
//                            MainApplication.applicationContext(),
//                            MainApplication.applicationContext()
//                                .getString(R.string.internal_server_error),
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
//                    Resource.error(
//                        MainApplication.applicationContext()
//                            .getString(R.string.internal_server_error),
//                        null
//                    )
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    Resource.error(
//                        MainApplication.applicationContext()
//                            .getString(R.string.str_something_went_wrong), null
//                    )
//                }
//            }
////            if (newsSource != null && newsSource.isSuccessful) {
////                responseMap[AppConstant.API_URL] = newsSource.raw().request.url.toString()
////                responseMap[AppConstant.API_RESPONSE_CODE] = newsSource.code().toString()
////                responseMap[AppConstant.API_RESPONSE_BODY] = newsSource.body()!!.string()!!
////                emit(responseMap)
////            } else {
////                try {
////                    val gson = Gson()
////                    val responseModel =
////                        gson?.fromJson(
////                            newsSource!!.errorBody()!!.charStream(),
////                            com.dogfriendlynz.model.Response::class.java
////                        )
////                    responseMap[AppConstant.API_URL] = SuffixURL
////                    responseMap[AppConstant.API_RESPONSE_CODE] = 1001.toString()
////                    responseMap[AppConstant.API_RESPONSE_BODY] = responseModel.message
////                } catch (e: Exception) {
////                    responseMap[AppConstant.API_URL] = SuffixURL
////                    responseMap[AppConstant.API_RESPONSE_CODE] = 1001.toString()
////                    responseMap[AppConstant.API_RESPONSE_BODY] = "Something went wrong"
////                    e.printStackTrace()
////                }
////                emit(responseMap)
////            }
////        }
////        }
//    }
//
//    private suspend fun getAccessTokenApi(
//        callMethod: @AppConstant.CallMethod Int,
//        strURL: String,
//        strAccessToken: String,
//        isAuthenticationRequired: Boolean,
//        requestHashMap: HashMap<String, String>
//    ): Resource<HashMap<String, String>> {
//        val accessTokenRequest = AccessTokenRequest()
//        accessTokenRequest.refreshToken = PrefModel.refreshToken
//        val accessTokenOld = AppConstant.ACCESS_TOKEN_TYPE + " " + PrefModel.accessToken
//        val newsSource = apiHelper.getUserAccessTokenApi(accessTokenRequest)
//        if (newsSource.code() == AppConstant.ResponseStatusCode.STATUS_200) {
//            val refreshTokenResponseMap = HashMap<String, String>()
//            refreshTokenResponseMap[AppConstant.API_URL] = newsSource.raw().request.url.toString()
//            refreshTokenResponseMap[AppConstant.API_RESPONSE_CODE] = newsSource.code().toString()
//            refreshTokenResponseMap[AppConstant.API_RESPONSE_BODY] = newsSource.body()!!.string()
//            val strResponseBody: String =
//                refreshTokenResponseMap[AppConstant.API_RESPONSE_BODY]!!
//            val apiResponseModel = convertJsonToClass(
//                strResponseBody,
//                AccessTokenResponseModel::class.java
//            )
//            if (requestHashMap.isNotEmpty()) {
//                val strOldResponseBody: String =
//                    requestHashMap[AppConstant.REQUEST_PARAMS_POST_BODY_REQUEST_KEY].toString()
//                val strOldAccessToken: String =
//                    requestHashMap["accessToken"].toString()
//                AppLogger.debug("ACCESS-TOKEN-OLD=$strOldResponseBody")
//                AppLogger.debug("ACCESS-TOKEN-OLD1=$strOldAccessToken")
//                AppLogger.debug("REQUEST PARAMETER=${requestHashMap.toString()}")
//                if (strOldResponseBody != null && strOldResponseBody != "" && strOldResponseBody != "null") {// Handle for change password api request parameter
//                    val strNewResponseBody = strOldResponseBody.replace(accessTokenOld.toRegex()) {
//                        AppConstant.ACCESS_TOKEN_TYPE + " " + apiResponseModel.data!!.access_token
//                    }
//                    AppLogger.debug("ACCESS-TOKEN=$strNewResponseBody")
//                    if (strNewResponseBody != null && strNewResponseBody != "" && strNewResponseBody != "null") {
//                        requestHashMap[AppConstant.REQUEST_PARAMS_POST_BODY_REQUEST_KEY] =
//                            strNewResponseBody
//                    }
//                } else if (strOldAccessToken != null && strOldAccessToken != "" && strOldAccessToken != "null") { // Handle for logout api parameter
//                    val strNewResponseBody =
//                        strOldAccessToken.replace(PrefModel.accessToken.toRegex()) {
//                            apiResponseModel.data!!.access_token
//                        }
//                    AppLogger.debug("ACCESS-TOKEN1=$strNewResponseBody")
//                    if (strNewResponseBody != null && strNewResponseBody != "" && strNewResponseBody != "null") {
//                        requestHashMap["accessToken"] =
//                            strNewResponseBody
//                    }
//                }
//            }
//            AppLogger.debug("REQUEST PARAMETER1=${requestHashMap.toString()}")
//            PrefModel.accessToken = apiResponseModel.data!!.access_token
//            PrefModel.refreshToken = apiResponseModel.data!!.refresh_token
//            PrefModel.expiresIn = apiResponseModel.data!!.expires_in
//            return apiCallingMethod(callMethod, strURL, isAuthenticationRequired, requestHashMap)
//        } else {
//            clearUserDataToPreference()
//            val intent = Intent(MainApplication.applicationContext(), LoginActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            (MainApplication.applicationContext()).startActivity(intent)
//            CoroutineScope(Job() + Dispatchers.Main).launch {
//                makeText(
//                    MainApplication.applicationContext(),
//                    MainApplication.applicationContext().getString(R.string.str_logout_success),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//            return Resource.error(
//                "",
//                null
//            )
//        }
//    }

    private fun clearUserDataToPreference() {
        PrefModel.isUserLoggedIn = true

        PrefModel.userId = 0
        PrefModel.roleId = 0
        PrefModel.userName = ""

        PrefModel.roleName = ""
        PrefModel.isActive = 0
        PrefModel.accessToken = ""
        PrefModel.userMobile = ""
        PrefModel.userEmail = ""
        PrefModel.region = ""
        PrefModel.workStatus = 1
        PrefModel.attndStatus = 1
    }

//    suspend fun getNearByOutletList(
//        verticalCode: String,
//        latitude: String,
//        longitude: String
//    ): Flow<Resource<NearByOutletResponse>> {
////        return withContext(Dispatchers.IO) {
////            val newsSource = apiHelper.getNearByOutletList(verticalCode, latitude, longitude)
////            if (newsSource.isSuccessful) {
////                Resource.success(newsSource.body())
////            } else {
////               Resource.error(newsSource.message(), null)
////            }
////        }
//        return flow {
//            val newsSource = apiHelper.getNearByOutletList(verticalCode, latitude, longitude)
//            if (newsSource.isSuccessful) {
//                emit(Resource.success(newsSource.body()))
//            } else {
//                emit(Resource.error(newsSource.message(), null))
//            }
//        }
//    }

//    suspend fun getRecentAppointmentList(
//        requestParameter: String
//    ): Flow<Resource<RecentAppointmentResponse>> {
//        return flow {
//            val newsSource = apiHelper.getRecentBookedAppointmentList(requestParameter)
//            if (newsSource.isSuccessful) {
//                emit(Resource.success(newsSource.body()))
//
//            } else {
//                emit(Resource.error(newsSource.message(), null))
//            }
//        }
//    }


//    suspend fun holidayList(
//        businessBranchSetupCode: String,
//        date: String
//
//    ): Flow<Resource<HolidayListResponseModel>> {
//        return flow {
//            val newsSource = apiHelper.holidayList(businessBranchSetupCode, date)
//            if (newsSource.isSuccessful) {
//                emit(Resource.success(newsSource.body()))
//
//            } else {
//                emit(Resource.error(newsSource.message(), null))
//            }
//        }
//    }

//    suspend fun bookApointmentApi(bookAppointmetListingRequest: BookAppointmetListingRequest): Resource<BookAppointmentListResponseModel> {
//
//        val newsSource = apiHelper.bookApointment(bookAppointmetListingRequest)
//        if (newsSource.isSuccessful) {
//            return Resource.success(newsSource.body())
//        } else {
//            return Resource.error(newsSource.message(), null)
//        }
//
//    }

//    suspend fun srvTimeByStartTimeApi(srvTimeByStartTimeRequest: SrvTimeByStartTimeRequest):
//            Resource<SrvTimeByStartTimeResponseModel> {
//
//        val newsSource = apiHelper.srvTimeByStartTime(srvTimeByStartTimeRequest)
//        if (newsSource.isSuccessful) {
//            return Resource.success(newsSource.body())
//        } else {
//            return Resource.error(newsSource.message(), null)
//        }
//
//    }

//    suspend fun availableTimeSlotList(
//        date: String,
//        outletId: String,
//        branchId: String,
//        serviceId: String,
//        selectedStartTime: ArrayList<String>
//
//    ): Flow<Resource<EstimatedTimeListResponseModel>> {
//        return flow {
//            val newsSource = apiHelper.availableTimeSlotList(date, outletId, branchId, serviceId,selectedStartTime)
//            if (newsSource.isSuccessful) {
//                emit(Resource.success(newsSource.body()))
//
//            } else {
//                emit(Resource.error(newsSource.message(), null))
//            }
//        }
//    }
//
//    suspend fun workingHrsBasedDate(
//        date: String,
//        outletId: String,
//        branchId: String
//
//    ): Flow<Resource<WorkingHrsDateResponseModel>> {
//        return flow {
//            val newsSource = apiHelper.workingHrsBasedDate(date, outletId, branchId)
//            if (newsSource.isSuccessful) {
//                emit(Resource.success(newsSource.body()))
//
//            } else {
//                emit(Resource.error(newsSource.message(), null))
//            }
//        }
//    }
}

