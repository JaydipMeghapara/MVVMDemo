@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.jaydip.mvvmdemo.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.gson.Gson
import com.jaydip.mvvmdemo.R
import com.jaydip.mvvmdemo.util.AppConstant.DATE_FORMAT_DISPLAY
import com.jaydip.mvvmdemo.util.AppConstant.DATE_FORMAT_SERVER
import com.jaydip.mvvmdemo.util.AppConstant.INTENT_KEY_LOCATION_PERMISSION_REQUEST
import com.jaydip.mvvmdemo.util.AppConstant.TIME_FORMAT_DISPLAY
import com.jaydip.mvvmdemo.util.AppConstant.TIME_FORMAT_DISPLAY_12_Hours
import de.hdodenhof.circleimageview.CircleImageView
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Extension function to start/launch new [Activity] without passing any data
 * */
inline fun <reified T : Activity> Activity.navigateToAndFinish() {
    this.apply {
        startActivity(Intent(this, T::class.java))
        finish()
    }
}

inline fun <reified T : Activity> Activity.navigateToAndFinishClear() {
    this.apply {
        val intent = Intent(this, T::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}

/**
 * Extension function for observing [LiveData]
 * @param owner is [LifecycleOwner] which will be used to listen lifecycle changes
 * @param func is a function which will be executed whenever [LiveData] is changed
 * */
fun <T> LiveData<T>.watch(owner: LifecycleOwner, func: (T) -> Unit) =
    this.observe(owner, Observer<T> { t -> t?.let(func) })

/**
 * Extension function for observing [LiveData]
 * @param owner is [LifecycleOwner] which will be used to listen lifecycle changes
 * @param func is a function which will be executed whenever [LiveData] is changed
 * */
fun <T> MutableLiveData<T>.watch(owner: LifecycleOwner, func: (T) -> Unit) =
    this.observe(owner, Observer<T> { t -> t?.let(func) })


fun <T> Context.toast(obj: T) =
    Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show()

fun <T> Fragment.toast(obj: T) = this.requireContext().toast(obj)
fun <T> MutableLiveData<T>.notify() {
    this.value = this.value
}

/**
 * Format milis to minute to sec
 **/
fun Long.formatTime(): String {
    return String.format(
        "%02d:%02d",
        TimeUnit.MILLISECONDS.toMinutes(this) % TimeUnit.HOURS.toMinutes(
            1
        ),
        TimeUnit.MILLISECONDS.toSeconds(this) % TimeUnit.MINUTES.toSeconds(
            1
        )
    )
}


/**
 * Load image using url with Glide
 * @receiver AppCompatImageView
 * @param url String
 */
fun AppCompatImageView.loadImage(
    url: Any?,
    isCircle: Boolean = false,
    isRounded: Boolean = false,
    isCenterCrop: Boolean = false,
    radius: Int = 6,
    func: RequestOptions.() -> Unit
) {
    url?.let { image ->
        val options =
            RequestOptions().placeholder(R.drawable.ic_store_default)
                .error(R.drawable.ic_store_default)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(func)
        val requestBuilder = Glide.with(context).load(image).apply(options)
        if (isCircle) {
            requestBuilder.apply(options.circleCrop())
        }
        if (isRounded) {
            requestBuilder.transform(RoundedCorners(radius))
        }
        if (isCenterCrop) {
            requestBuilder.apply(options.centerCrop())
        }
        requestBuilder.into(this)
    }
}

/**
 * Load image using url with Glide
 * @receiver AppCompatImageView
 * @param url String
 */
fun CircleImageView.loadImage(
    url: Any?,
    isCircle: Boolean = false,
    isRounded: Boolean = false,
    isCenterCrop: Boolean = false,
    radius: Int = 6,
    func: RequestOptions.() -> Unit
) {
    url?.let { image ->
        val options =
            RequestOptions().placeholder(R.drawable.ic_store_default)
                .error(R.drawable.ic_store_default)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(func)
        val requestBuilder = Glide.with(context).load(image).apply(options)
        if (isCircle) {
            requestBuilder.apply(options.circleCrop())
        }
        if (isRounded) {
            requestBuilder.transform(RoundedCorners(radius))
        }
        if (isCenterCrop) {
            requestBuilder.apply(options.centerCrop())
        }
        requestBuilder.into(this)
    }
}

/**
 * Extension Function for initializing [MutableLiveData] with some initial value
 * @param data is the initial value
 * */
fun <T> MutableLiveData<T>.initWith(data: T): MutableLiveData<T> = this.apply {
    value = data
}

fun Float.formatDecimal(): String {
    return DecimalFormat("0.0").format(this)
}

fun Float.formatNoDecimal(): String {
    val temp = String.format(java.util.Locale.US, "%.2f", this)
    return if (temp.endsWith(".0")) {
        temp.dropLast(2)
    } else if (temp.endsWith(".00")) {
        temp.dropLast(3)
    } else {
        temp
    }
}

fun AppCompatEditText.keyboardOpen() {
    val inputMethodManager: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInputFromWindow(
        applicationWindowToken,
        InputMethodManager.SHOW_FORCED, 0
    )
}

fun AppCompatEditText.keyboardHide() {
    val inputMethodManager: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        applicationWindowToken, 0
    )
}

fun View.disableView() {
    val colorFilter =
        PorterDuffColorFilter(
            ContextCompat.getColor(context, R.color.colorDisable),
            PorterDuff.Mode.SRC_ATOP
        )
    background.colorFilter = colorFilter
    isClickable = false
    isEnabled = false
}

fun ViewGroup.disableEnableControls(enable: Boolean) {
    for (i in 0 until childCount) {
        val child: View = getChildAt(i)
        child.isEnabled = enable
        if (child is ViewGroup) {
            child.disableEnableControls(enable)
        }
    }
}

/*fun Activity.disableTouch() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}*/

/*fun Activity.enableTouch() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}*/

fun AppCompatTextView.setStatusColor(status: String) {
    when (status.toLowerCase(Locale.ROOT)) {
        "confirmed" -> {
            setTextColor(ContextCompat.getColor(context, R.color.colorDisable))
        }
        "rejected" -> {
            setTextColor(ContextCompat.getColor(context, R.color.colorDisable))
        }
    }
}

fun String.parseDate(
    format: String = DATE_FORMAT_SERVER, convertFormat: String = DATE_FORMAT_DISPLAY
): String {
    try {
        val formatDateServer = SimpleDateFormat(format, Locale.getDefault())
        val date = formatDateServer
            .parse(this) ?: return this
        return SimpleDateFormat(convertFormat, Locale.getDefault()).format(date).toLowerCase()
    } catch (e: Exception) {
        return this
    }
}

fun currentDate(format: String = "yyyy-MM-dd"): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(Date())
}

fun AppCompatImageView.addressType(type: String) {
    when (type) {
        "home" -> {
            this.setImageResource(R.drawable.ic_launcher_background)
        }
        "work" -> {
            this.setImageResource(R.drawable.ic_launcher_background)
        }
        else -> {
            this.setImageResource(R.drawable.ic_launcher_background)
        }
    }
}

fun String.makeCapsFirst(): String {
    return this.substring(0, 1).toUpperCase() + this.substring(1).toLowerCase()
}

fun Context.isGPSOn(): Boolean {
    val locationManager: LocationManager = getSystemService(LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}

fun Fragment.isGPSOn(): Boolean {
    val locationManager: LocationManager =
        requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}

fun Fragment.openLocationSetting() {
    MaterialAlertDialogBuilder(requireContext()).setMessage(getString(R.string.enable_gps_msg))
        .setPositiveButton(
            getString(R.string.str_yes)
        ) { p0, _ ->
            p0.dismiss()
            startActivity(Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        }.setNegativeButton(
            getString(R.string.str_cancel)
        ) { p0, _ ->
            p0.dismiss()
        }.show()
}

fun String.parseEventDate(
    format: String = DATE_FORMAT_SERVER
): Date {
    val formatDateServer = SimpleDateFormat(format, Locale.getDefault())
    formatDateServer.timeZone = TimeZone.getTimeZone("UTC")
    return formatDateServer.parse(this)
}

fun String.parseDateUs(
    format: String = DATE_FORMAT_SERVER, convertFormat: String = DATE_FORMAT_DISPLAY
): String {
    try {
        val formatDateServer = SimpleDateFormat(format, Locale.getDefault())
//        formatDateServer.timeZone = TimeZone.getTimeZone("UTC")
        val date = formatDateServer
            .parse(this) ?: return this
        return SimpleDateFormat(convertFormat, Locale.getDefault()).format(date)
    } catch (e: Exception) {
        return this
    }
}

fun String.parseTimeUs(
    format: String = DATE_FORMAT_SERVER, convertFormat: String = TIME_FORMAT_DISPLAY
): String {
    try {
        val formatDateServer = SimpleDateFormat(format, Locale.getDefault())
//        formatDateServer.timeZone = TimeZone.getTimeZone("UTC")
        val time = formatDateServer
            .parse(this) ?: return this
        return SimpleDateFormat(convertFormat, Locale.getDefault()).format(time)
    } catch (e: Exception) {
        return this
    }
}

fun String.parseOrderDateTime(
    format: String = DATE_FORMAT_SERVER, convertFormat: String = DATE_FORMAT_DISPLAY
): String {
    try {
        val formatDateServer = SimpleDateFormat(format, Locale.getDefault())
//        formatDateServer.timeZone = TimeZone.getTimeZone("UTC")
        val time = formatDateServer
            .parse(this) ?: return this
        val nowTime = Date()
        val todayCal = Calendar.getInstance()
        val serverCal = Calendar.getInstance()
        todayCal.time = nowTime
        serverCal.time = time

        val oldYear: Int = serverCal.get(Calendar.YEAR)
        val year: Int = todayCal.get(Calendar.YEAR)
        val oldDay: Int = serverCal.get(Calendar.DAY_OF_YEAR)
        val day: Int = todayCal.get(Calendar.DAY_OF_YEAR)

        if (oldYear === year) {
            return when (oldDay - day) {
                0 -> {
                    SimpleDateFormat(TIME_FORMAT_DISPLAY_12_Hours, Locale.getDefault()).format(
                        serverCal.time
                    ) + ", Today"
                }
                1 -> {
                    SimpleDateFormat(TIME_FORMAT_DISPLAY_12_Hours, Locale.getDefault()).format(
                        serverCal.time
                    ) + ", Tomorrow"
                }
                else -> {
                    SimpleDateFormat(DATE_FORMAT_DISPLAY, Locale.getDefault()).format(serverCal.time)
                }
            }
        }
        return SimpleDateFormat(convertFormat, Locale.getDefault()).format(serverCal.time)
    } catch (e: Exception) {
        return this
    }
}

fun hideOpenKeyboard(context: Activity) {
    try {
        context.window
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        if ((context.currentFocus != null) && (context.currentFocus!!.windowToken != null)) {
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                context.currentFocus!!.windowToken,
                0
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun showKeyboard(context: Activity) {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
        InputMethodManager.SHOW_FORCED,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}

fun isNetworkAvailable(context: Context?): Boolean {
    if (context == null) return false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
    }
    return false
}

fun checkPermissionLocation(context: Activity?) {
    ActivityCompat.requestPermissions(
        context!!,
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ),
        INTENT_KEY_LOCATION_PERMISSION_REQUEST
    )
}

fun <T> convertJsonToClass(strResponse: String?, type: Class<T>?): T {
    val gsonConverter = Gson()
    return gsonConverter.fromJson(strResponse, type)
}

@SuppressLint("SimpleDateFormat")
fun convertDate(inputFormat: String, date: Date): Date {

    return SimpleDateFormat(inputFormat).parse(date.toString())


}