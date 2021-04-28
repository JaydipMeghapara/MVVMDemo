package com.jaydip.mvvmdemo.ui.help

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.jaydip.mvvmdemo.repository.MainRepository
import com.jaydip.mvvmdemo.util.NetworkHelper

class HelpViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel(), LifecycleObserver {

//    val verticalList = MutableLiveData<Resource<VerticalListResponseModel>>()
//    val vertical: LiveData<Resource<VerticalListResponseModel>>
//        get() = verticalList

//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    private fun onActivityResume() {
//
//    }

    public override fun onCleared() {
        super.onCleared()
    }

}