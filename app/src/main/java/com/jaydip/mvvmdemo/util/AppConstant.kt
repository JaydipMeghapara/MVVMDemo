package com.jaydip.mvvmdemo.util

import androidx.annotation.IntDef

/**
 * Created by Mayur Solanki on 30/09/20, 11:39 AM.
 */
object AppConstant {
    const val  REACHABILITY_SERVER : String = "https://www.google.com"
    const val DATE_FORMAT_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_FORMAT_SERVER1 = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT_DISPLAY = "hh:mm a, EEEE"
    const val BOOKING_DATE_FORMAT_DISPLAY = "dd MMMM EE MMM yyyy"
    const val BOOKING_DATE_TODAY_FORMAT_DISPLAY = "EE MMM dd HH:mm:ss zzzz yyyy"
    const val BOOKING_DATE_YMD_FORMAT_DISPLAY = "yyyy-MM-dd"
    const val BOOKING_DATE_DMY_FORMAT_DISPLAY = "dd/MM/yyyy"
    const val TIME_FORMAT_DISPLAY = "HH:mm"
    const val TIME_FORMAT_DISPLAY_FILTER = "HH:mm"
    const val TIME_FORMAT_DISPLAY_12_Hours = "hh:mm a"
    const val viewModel = 1
    const val RESPONSE_200 = 200
    const val RESPONSE_404= 404
    const val RESPONSE_400= 400
    const val RESPONSE_201 = 201
    const val INTENT_KEY_LOCATION_PERMISSION_REQUEST = 34
    const val INTENT_KEY_GPS_PERMISSION_REQUEST = 5


    const val ACCESS_TOKEN_TYPE: String = "Bearer"

    const val PREF_QA_SERVER = "pref_qa_server"
    const val PREF_DEV_SERVER = "pref_dev_server"
    const val PREF_UAT_SERVER = "pref_uat_server"


    const val REQUEST_PARAMS_POST_BODY_REQUEST_KEY = "request_params_post_body_request_key"

    const val API_URL = "api_url"
    const val API_RESPONSE_CODE = "api_response_code"
    const val API_RESPONSE_BODY = "api_response_body"

    const val GET = 1000
    const val POST = 1001
    const val MULTIPART_POST = 1002
    const val POST_BODY = 1003
    const val DELETE = 1004
    const val PUT = 1006
    const val PUT_BODY = 1007
    const val MULTIPART_PUT = 1008
    const val DELETE_BODY = 1009

    @Target(AnnotationTarget.EXPRESSION, AnnotationTarget.TYPE)
    @IntDef(GET, POST, MULTIPART_POST, POST_BODY, DELETE, PUT, PUT_BODY, DELETE_BODY)
    @Retention(AnnotationRetention.SOURCE)
    annotation class CallMethod

//    @Target(AnnotationTarget.TYPE)
//    @IntDef(*[GET, POST, MULTIPART_POST, POST_BODY, DELETE, PUT, PUT_BODY])
//    @Retention(
//        RetentionPolicy.SOURCE
//    )
//    annotation class CallMethod

    object ResponseStatusCode {
        const val STATUS_200 = 200
        const val STATUS_201 = 201
        const val STATUS_204 = 204
        const val STATUS_400 = 400
        const val STATUS_401 = 401
        const val STATUS_403 = 403
        const val STATUS_404 = 404
        const val STATUS_407 = 407
        const val STATUS_422 = 422
        const val STATUS_429 = 429
        const val STATUS_500 = 500
        const val STATUS_302 = 302
    }

    object AppointmentStatusCode {
        const val STATUS_CONFIRM = 1
        const val STATUS_CONFIRM_CHECK_IN = 2
        const val STATUS_ONGOING_SERVING = 3
        const val STATUS_COMPLETED = 4
        const val STATUS_NO_SHOW = 5
        const val STATUS_CANCELLED = 6
    }

}