package com.jaydip.mvvmdemo.ui.orderlist

import androidx.lifecycle.*
import com.google.gson.Gson
import com.jaydip.mvvmdemo.api.request.OrderListRequest
import com.jaydip.mvvmdemo.api.response.OrderListResponse
import com.jaydip.mvvmdemo.repository.MainRepository
import com.jaydip.mvvmdemo.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OrderListViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel(), LifecycleObserver {

    private val _orderList = SingleLiveEvent<Resource<OrderListResponse>>()

    val orderList: LiveData<Resource<OrderListResponse>>
        get() = _orderList

    fun getOrderListAPI(
        pageNo: Int,
        perPage: Int,
        isFromCompletedTab: Boolean,
        booleanIsProgressRequired: Boolean
    ) {
        val listType: String = if (isFromCompletedTab) {
            "past"
        } else {
            "ongoing"
        }
        val orderListRequest =
            OrderListRequest(listType, pageNo, perPage)
        viewModelScope.launch(Dispatchers.IO) {
            val requestMap: HashMap<String, String> = HashMap()
            requestMap[AppConstant.REQUEST_PARAMS_POST_BODY_REQUEST_KEY] =
                Gson().toJson(orderListRequest)
            val apiResponse = mainRepository.apiCallingMethod(
                AppConstant.POST_BODY,
                URLConstant.URL_POST_ORDER_LIST, true,
                requestMap
            )
            CoroutineScope(Dispatchers.IO).launch {
                if (booleanIsProgressRequired) {
                    _orderList.postValue(Resource.loading(null))
                }
                apiResponse.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            val responseHashMap: HashMap<String, String> = it.data!!
                            val apiResponseModel = convertJsonToClass(
                                responseHashMap[AppConstant.API_RESPONSE_BODY]!!,
                                OrderListResponse::class.java
                            )
                            _orderList.postValue(Resource.success(apiResponseModel))
                        }
                        Status.ERROR -> _orderList.postValue(
                            Resource.error(
                                it.message ?: "Error Occurred!", null
                            )
                        )
                        Status.LOADING -> _orderList.postValue(Resource.loading(null))
                    }
                }
            }
        }
    }


//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    private fun onActivityResume() {
//
//    }

    public override fun onCleared() {
        super.onCleared()
    }

}