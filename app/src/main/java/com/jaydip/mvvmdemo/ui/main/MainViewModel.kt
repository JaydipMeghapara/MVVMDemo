package com.jaydip.mvvmdemo.ui.main

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.jaydip.mvvmdemo.repository.MainRepository
import com.jaydip.mvvmdemo.util.NetworkHelper


class MainViewModel(private val mainRepository: MainRepository, private val networkHelper: NetworkHelper): ViewModel(),LifecycleObserver{

    private val context: Context? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onActivityResume(){
    }

    override fun onCleared() {
        super.onCleared()
    }

}