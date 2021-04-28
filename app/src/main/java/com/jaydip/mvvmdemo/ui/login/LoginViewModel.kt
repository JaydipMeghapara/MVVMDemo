package com.jaydip.mvvmdemo.ui.login

import androidx.lifecycle.*
import com.google.gson.Gson
import com.jaydip.mvvmdemo.api.request.LoginRequest
import com.jaydip.mvvmdemo.api.request.OtpVerifyRequest
import com.jaydip.mvvmdemo.api.response.LoginResponse
import com.jaydip.mvvmdemo.api.response.OtpVerifyResponse
import com.jaydip.mvvmdemo.model.PrefModel
import com.jaydip.mvvmdemo.repository.MainRepository
import com.jaydip.mvvmdemo.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class LoginViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel(), LifecycleObserver {

    private val _loginResponse = MutableLiveData<Resource<LoginResponse>>()
    private val _otpVerifyResponse = MutableLiveData<Resource<OtpVerifyResponse>>()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse
    val otpVerifyResponse: LiveData<Resource<OtpVerifyResponse>>
        get() = _otpVerifyResponse

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onActivityResume() {
    }

    override fun onCleared() {
        super.onCleared()
    }

    //network call
    fun callLoginApi(mobileNo: String) {
        val loginRequest = createLoginRequest(mobileNo)

        viewModelScope.launch(Dispatchers.IO) {
            _loginResponse.postValue(Resource.loading(null))
            val requestMap: HashMap<String, String> = HashMap()
            requestMap[AppConstant.REQUEST_PARAMS_POST_BODY_REQUEST_KEY] =
                Gson().toJson(loginRequest)

            val apiResponse = mainRepository.apiCallingMethod(
                AppConstant.POST_BODY,
                URLConstant.URL_POST_LOGIN, false,
                requestMap
            )
//                val apiResponse = mainRepository.doLoginEmailPwdWithApi(loginRequest)
            CoroutineScope(Dispatchers.IO).launch {
                apiResponse.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            val responseHashMap: HashMap<String, String> = it.data!!
                            val apiResponseModel = convertJsonToClass(
                                responseHashMap[AppConstant.API_RESPONSE_BODY]!!,
                                LoginResponse::class.java
                            )
                            _loginResponse.postValue(Resource.success(apiResponseModel))
                        }

                        Status.ERROR -> _loginResponse.postValue(
                            Resource.error(
                                it.message ?: "Error Occurred!", null
                            )
                        )

                        Status.LOADING -> _loginResponse.postValue(Resource.loading(null))
                    }
                }
            }
        }
    }

    private fun createLoginRequest(mobileNo: String): LoginRequest {
        val loginRequest = LoginRequest()
        loginRequest.envinfo = "test#RetailEndUser"
        loginRequest.mobile =mobileNo
        return loginRequest
    }

    //network call
    fun callOtpVVerifyApi(mobileNo: String,otp:Int) {
        val otpRequest = createOtpVerifyRequest(mobileNo,otp)

        viewModelScope.launch(Dispatchers.IO) {
            _otpVerifyResponse.postValue(Resource.loading(null))
            val requestMap: HashMap<String, String> = HashMap()
            requestMap[AppConstant.REQUEST_PARAMS_POST_BODY_REQUEST_KEY] =
                Gson().toJson(otpRequest)

            val apiResponse = mainRepository.apiCallingMethod(
                AppConstant.POST_BODY,
                URLConstant.URL_POST_VALIDATE_OTP, false,
                requestMap
            )
//                val apiResponse = mainRepository.doLoginEmailPwdWithApi(loginRequest)
            CoroutineScope(Dispatchers.IO).launch {
                apiResponse.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            val responseHashMap: HashMap<String, String> = it.data!!
                            val apiResponseModel = convertJsonToClass(
                                responseHashMap[AppConstant.API_RESPONSE_BODY]!!,
                                OtpVerifyResponse::class.java
                            )
                            _otpVerifyResponse.postValue(Resource.success(apiResponseModel))
                        }

                        Status.ERROR -> _otpVerifyResponse.postValue(
                            Resource.error(
                                it.message ?: "Error Occurred!", null
                            )
                        )

                        Status.LOADING -> _loginResponse.postValue(Resource.loading(null))
                    }
                }
            }
        }
    }

    private fun createOtpVerifyRequest(mobileNo: String,otpNo: Int): OtpVerifyRequest {
        val otpVerifyRequest = OtpVerifyRequest()
        otpVerifyRequest.envinfo = "test#RetailEndUser"
        otpVerifyRequest.mobile =mobileNo
        otpVerifyRequest.otherinfo ="otherinfo"
        otpVerifyRequest.versioninfo ="versioninfo"
        otpVerifyRequest.fcm ="fcm"
        otpVerifyRequest.otp =otpNo
        return otpVerifyRequest
    }

    fun saveUserDataToPreference(loggedUserData: OtpVerifyResponse.Data) {
        PrefModel.isUserLoggedIn = true

        PrefModel.userId = loggedUserData.user_id
        PrefModel.roleId = loggedUserData.role_id
        PrefModel.userName = loggedUserData.name

        PrefModel.roleName = loggedUserData.role_name
        PrefModel.isActive = loggedUserData.is_active
        PrefModel.accessToken = loggedUserData.token
        PrefModel.userMobile = loggedUserData.mobile
        PrefModel.userEmail = loggedUserData.email
//        PrefModel.region = loggedUserData.region
        PrefModel.workStatus = loggedUserData.work_status
        PrefModel.attndStatus = loggedUserData.attnd_status
    }
}