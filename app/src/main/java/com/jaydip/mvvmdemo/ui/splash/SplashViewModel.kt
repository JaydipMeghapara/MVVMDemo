package com.jaydip.mvvmdemo.ui.splash

import androidx.lifecycle.*
import com.jaydip.mvvmdemo.model.PrefModel
import com.jaydip.mvvmdemo.repository.MainRepository
import com.jaydip.mvvmdemo.util.NetworkHelper
import kotlinx.coroutines.launch


class SplashViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel(), LifecycleObserver {
    val isUserLogin = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
//            delay(3000L)
            isUserLogin.postValue(PrefModel.isUserLoggedIn)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onActivityResume() {

    }

    override fun onCleared() {
        super.onCleared()
    }



}