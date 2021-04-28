package com.jaydip.mvvmdemo.di

import com.jaydip.mvvmdemo.ui.help.HelpViewModel
import com.jaydip.mvvmdemo.ui.home.HomeViewModel
import com.jaydip.mvvmdemo.ui.orderlist.OrderListViewModel
import com.jaydip.mvvmdemo.ui.login.LoginViewModel
import com.jaydip.mvvmdemo.ui.main.MainViewModel
import com.jaydip.mvvmdemo.ui.profile.ProfileViewModel
import com.jaydip.mvvmdemo.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        SplashViewModel(get(), get())
    }
    viewModel {
        MainViewModel(get(), get())
    }
    viewModel {
        LoginViewModel(get(), get())
    }
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        HelpViewModel(get(), get())
    }
    viewModel {
        ProfileViewModel(get(), get())
    }
    viewModel {
        OrderListViewModel(get(), get())
    }
}

//  mainViewModelModule is NOT providing any thing. so no provide Method , instead it injecting dependency which is someone is providing it.
