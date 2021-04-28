package com.jaydip.mvvmdemo.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaydip.mvvmdemo.repository.MainRepository
import com.jaydip.mvvmdemo.util.NetworkHelper
import kotlinx.coroutines.launch

/**
 * Created by Mayur Solanki on 29/09/20, 1:16 PM.
 */
class PrefsViewModel(private val mainRepository: MainRepository, private val networkHelper: NetworkHelper) : ViewModel() {

    init {
        networkHelper.isInternetConnected()
    }

    fun setValue(data: String) {
        viewModelScope.launch {
//            mainRepository.setValueToDataStore(data)
        }
    }

    fun getValue() {
        viewModelScope.launch {
//            mainRepository.getValueFromDataStore().collect {
//                AppLogger.debug()
//            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}