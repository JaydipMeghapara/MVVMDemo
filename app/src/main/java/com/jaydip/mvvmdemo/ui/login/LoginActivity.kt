package com.jaydip.mvvmdemo.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.jaydip.mvvmdemo.R
import com.jaydip.mvvmdemo.databinding.LoginActivityBinding
import com.jaydip.mvvmdemo.model.PrefGlobal
import com.jaydip.mvvmdemo.model.PrefModel
import com.jaydip.mvvmdemo.ui.base.BaseActivity
import com.jaydip.mvvmdemo.ui.main.MainActivity
import com.jaydip.mvvmdemo.util.*
import com.jaydip.mvvmdemo.view.Pinview
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber


class LoginActivity : BaseActivity<LoginActivityBinding, LoginViewModel>(R.layout.activity_login),
    LifecycleOwner {

    override val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        PrefGlobal.PREF_API_SERVER = AppConstant.PREF_QA_SERVER
        lifecycle.addObserver(viewModel)
        if (PrefModel.isUserLoggedIn){
            navigateToAndFinishClear<MainActivity>()
        }
        setupUI()
        setupObserver()


//        NetworkConnectivity.observe(this, Observer {
//            if (it) {
//                Timber.d(" MAIN Connected")
//
//            } else {
//                Timber.d("MAIN Connectivity Lost")
//            }
//        })
    }

    private fun setupObserver() {
        viewModel.loginResponse.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    actLoginBtnLogin.revertAnimation()

                    it.data.let { loginResponse ->
                        if (loginResponse != null) {
                            Timber.d("login response: %s", loginResponse)
                            when (loginResponse.status) {
                                200 -> {
                                    actLoginTvOtpNo.visibility=View.VISIBLE
                                    actLoginPinView.visibility=View.VISIBLE
                                    AppLogger.debug("actLoginPinView.value="+actLoginPinView.value)
                                    if (!actLoginPinView.value.isNullOrEmpty()) {
                                        actLoginPinView.clearValue()
                                    }
                                    toast("" + loginResponse.message)
                                }
                                else -> {
                                    toast("" + loginResponse.message)
                                }
                            }
                        }
                    }
                }

                Status.LOADING -> {
//                    pbLoginWithPwd.visibility = View.VISIBLE

                }
                Status.ERROR -> {
                    //Handle Error
                    actLoginBtnLogin.revertAnimation()
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.otpVerifyResponse.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    actLoginBtnLogin.revertAnimation()

                    it.data.let { loginResponse ->
                        if (loginResponse != null) {
                            Timber.d("login response: %s", loginResponse)
                            when (loginResponse.status) {
                                200 -> {
                                    loginResponse.data.let { it1 ->
                                        viewModel.saveUserDataToPreference(it1)
                                    }

                                    toast("" + loginResponse.message)
                                    startActivity(Intent(this, MainActivity::class.java))
                                    finish()
                                }
                                else -> {
                                    toast("" + loginResponse.message)
                                }
                            }
                        }
                    }
                }

                Status.LOADING -> {
//                    pbLoginWithPwd.visibility = View.VISIBLE

                }
                Status.ERROR -> {
                    //Handle Error
                    actLoginBtnLogin.revertAnimation()
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupUI() {
        hideOpenKeyboard(this)
        actLoginBtnLogin.setOnClickListener {
            checkValidationAndCallLoginApi()

        }
//        actLoginEtMobile.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (Utils.isValidMobileNumber(actLoginEtMobile.text.toString().trim())){
//                    checkValidationAndCallLoginApi()
//                }
//            }
//        })

        actLoginPinView.setPinViewEventListener { pinview, _ -> //Make api calls here or what not
            hideOpenKeyboard(LoginActivity@ this)
            if (isNetworkAvailable(LoginActivity@ this)) {
                actLoginBtnLogin.startAnimation()
                viewModel.callOtpVVerifyApi(
                    actLoginEtMobile.text.toString().trim(), pinview.value.toInt()
                )
            } else {
                toast(getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
            }
        }
    }
    private fun checkValidationAndCallLoginApi(){
        if (isNetworkAvailable(this)) {
            if (validateMobileEmailPassword()) {
                actLoginBtnLogin.startAnimation()
                viewModel.callLoginApi(
                    actLoginEtMobile.text.toString().trim()
                )
            }
        } else {
            toast(getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
        }
    }
    private fun validateMobileEmailPassword(): Boolean {
        var isValidMobileEmail = false
        when {
            actLoginEtMobile.text.toString().trim().isEmpty() -> {
                actLoginTilMobile.error = getString(R.string.email_mobile_number_required)
                isValidMobileEmail = false
            }
            Utils.isValidMobileNumber(actLoginEtMobile.text.toString().trim()) -> {
                if (actLoginTilMobile.isErrorEnabled) {
                    actLoginTilMobile.isErrorEnabled = false
                }
                isValidMobileEmail = true
            }
            else -> {
                actLoginTilMobile.error = getString(R.string.enter_valid_email_id_or_mobile)
                isValidMobileEmail = false
            }
        }
        return isValidMobileEmail
    }
    private fun clearPinViewChild() {
        for (i in 0 until actLoginPinView.childCount) {
            val child = actLoginPinView.getChildAt(i) as EditText
            child.setText("")
        }
    }

}


