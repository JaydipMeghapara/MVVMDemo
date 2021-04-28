package com.jaydip.mvvmdemo.ui.profile

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaydip.mvvmdemo.repository.MainRepository
import com.jaydip.mvvmdemo.util.NetworkHelper
import com.jaydip.mvvmdemo.util.initWith

class ProfileViewModel(
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