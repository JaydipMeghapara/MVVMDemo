package com.jaydip.mvvmdemo.ui.splash

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.jaydip.mvvmdemo.R
import com.jaydip.mvvmdemo.databinding.SplashActivityBinding
import com.jaydip.mvvmdemo.model.PrefGlobal
import com.jaydip.mvvmdemo.model.PrefModel
import com.jaydip.mvvmdemo.ui.base.BaseActivity
import com.jaydip.mvvmdemo.ui.login.LoginActivity
import com.jaydip.mvvmdemo.ui.main.MainActivity
import com.jaydip.mvvmdemo.util.AppConstant
import com.jaydip.mvvmdemo.util.navigateToAndFinishClear
import org.koin.android.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity<SplashActivityBinding, SplashViewModel>(R.layout.activity_splash) ,LifecycleOwner {

    override val viewModel: SplashViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        PrefGlobal.PREF_API_SERVER= AppConstant.PREF_QA_SERVER
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.isUserLogin.observe(this, Observer {
            when (it) {
                true -> {
                    navigateToAndFinishClear<MainActivity>()
                }
                false -> {
                    navigateToAndFinishClear<LoginActivity>()
                }
            }
        })
    }

}