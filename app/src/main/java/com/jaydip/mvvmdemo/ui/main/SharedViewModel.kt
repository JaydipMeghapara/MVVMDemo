package com.jaydip.mvvmdemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaydip.mvvmdemo.util.initWith

class SharedViewModel : ViewModel() {
    var isUpdated = MutableLiveData<Boolean>().initWith(false)
//    var appointmentDetail = MutableLiveData<AppointmentModel>()
//    var rescheduleAppointment : MutableList<AppointmentModel> = mutableListOf()

}
