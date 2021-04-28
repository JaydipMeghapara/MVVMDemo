package com.jaydip.mvvmdemo.ui.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.jaydip.mvvmdemo.repository.MainRepository
import com.jaydip.mvvmdemo.util.NetworkHelper

class HomeViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel(), LifecycleObserver {

//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    private fun onActivityResume() {
//
//    }

    public override fun onCleared() {
        super.onCleared()
    }

}