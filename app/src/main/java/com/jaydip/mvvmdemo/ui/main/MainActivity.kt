package com.jaydip.mvvmdemo.ui.main

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.forEach
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jaydip.mvvmdemo.R
import com.jaydip.mvvmdemo.databinding.MainActivityBinding
import com.jaydip.mvvmdemo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt


class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>(R.layout.activity_main),
    LifecycleOwner {
    private lateinit var navController: NavController

    //    private var currentNavController: LiveData<NavController>? = null
    override val viewModel: MainViewModel by viewModel()

//    private var myReceiver: MyReceiver? = null
//    private var mService: LocationUpdatesService? = null
//    private var mBound = false
//    private var isLocationDialogIsOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        myReceiver = MyReceiver()
        lifecycle.addObserver(viewModel)
        setupBottomNavigationBar()
//        if (!checkPermissions()) {
//            requestPermissions()
//        }
//        registerGpsOnOffReceiver()
    }


    private fun removePaddingFromNavigationItem() {
        val menuView = actMainBottomNav.getChildAt(0) as? ViewGroup ?: return
        menuView.forEach {
            it.findViewById<View>(R.id.largeLabel)?.setPadding(0, 0, 0, 0)
        }
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        navigationImagesMargin(actMainBottomNav)
        navController = findNavController(actMainNavHostContainer.id)
        NavigationUI.setupWithNavController(actMainBottomNav, navController)
        actMainBottomNav.setOnNavigationItemSelectedListener { item ->
            actMainBottomNav.post {
                navigationImagesMargin(actMainBottomNav)
            }
            when (item.itemId) {
                R.id.nav_menu_home -> {
                    val navOption = NavOptions.Builder().setLaunchSingleTop(true)
                        .setPopUpTo(R.id.nav_menu_home, true)
                    navController.navigate(R.id.nav_menu_home, null, navOption.build())
                }
                R.id.nav_menu_help -> {
                    val navOption = NavOptions.Builder().setLaunchSingleTop(true)
                        .setPopUpTo(R.id.nav_menu_home, false)
                    navController.navigate(R.id.nav_menu_help, null, navOption.build())
                }
                R.id.nav_menu_profile -> {
                    val navOption = NavOptions.Builder().setLaunchSingleTop(true)
                        .setPopUpTo(R.id.nav_menu_home, false)
                    navController.navigate(R.id.nav_menu_profile, null, navOption.build())
                }
            }
            true
        }
        actMainBottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.nav_menu_home -> {
                    return@setOnNavigationItemReselectedListener
                }
                R.id.nav_menu_help -> {
                    return@setOnNavigationItemReselectedListener
                }
                R.id.nav_menu_profile -> {
                    return@setOnNavigationItemReselectedListener
                }
            }
        }
        navController.addOnDestinationChangedListener { _,
                                                        destination, _ ->
            navigationImagesMargin(actMainBottomNav)
            when (destination.id) {
                R.id.nav_menu_home, R.id.nav_menu_help,
                R.id.nav_menu_profile -> {
                    actMainBottomNav.visibility = View.VISIBLE
                }
                else -> {
                    actMainBottomNav.visibility = View.GONE
                }
            }
        }
        removePaddingFromNavigationItem()
    }

    private fun navigationImagesMargin(view: View) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                navigationImagesMargin(child)
            }
        } else if (view is ImageView) {
            val param = view.layoutParams as ViewGroup.MarginLayoutParams
            param.topMargin = convertDpToPx(8)
            view.layoutParams = param
        }
    }

    private fun convertDpToPx(dp: Int): Int {
        return (dp * (resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }

    override fun onBackPressed() {
        val selectedItemId = actMainBottomNav.selectedItemId
        when (navController.currentDestination?.id) {
//            R.id.storeDetailFragment -> {super.onBackPressed()}
            R.id.nav_menu_home -> showMaterialDialog()
            else -> super.onBackPressed()
        }

    }

    private fun showMaterialDialog() {
        MaterialAlertDialogBuilder(
            this,
            R.style.AlertConfirmationDialog
        ).setTitle(getString(R.string.str_exit))
            .setMessage(getString(R.string.alert_exit))
            .setPositiveButton(
                getString(R.string.str_yes)
            ) { p0, _ ->
                p0.dismiss()
                super.onBackPressed()
            }.setNegativeButton(
                getString(R.string.str_cancel)
            ) { p0, _ ->
                p0.dismiss()
            }.show()
    }
//    override fun onStart() {
//        super.onStart()
//
//        // Bind to the service. If the service is in foreground mode, this signals to the service
//        // that since this activity is in the foreground, the service can exit foreground mode.
//        bindService(
//            Intent(this, LocationUpdatesService::class.java), mServiceConnection,
//            Context.BIND_AUTO_CREATE
//        )
//    }
//
//    override fun onStop() {
//        if (mBound) {
//            // Unbind from the service. This signals to the service that this activity is no longer
//            // in the foreground, and the service can respond by promoting itself to a foreground
//            // service.
//            unbindService(mServiceConnection)
//            mBound = false
//        }
//        super.onStop()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        LocalBroadcastManager.getInstance(this).registerReceiver(
//            myReceiver!!,
//            IntentFilter(LocationUpdatesService.ACTION_BROADCAST)
//        )
//    }
//
//    override fun onPause() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver!!)
//        super.onPause()
//    }
//
//    /**
//     * Receiver for broadcasts sent by [LocationUpdatesService].
//     */
//    private class MyReceiver : BroadcastReceiver() {
//        override fun onReceive(
//            context: Context,
//            intent: Intent
//        ) {
////            val location =
////                intent.getParcelableExtra<Location>(LocationUpdatesService.EXTRA_LOCATION)
////            if (location != null) {
////                AppLogger.debug("Location=(" + location.latitude + ", " + location.longitude + ")")
////            }
//        }
//    }
//
//    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
//        override fun onServiceConnected(name: ComponentName, service: IBinder) {
//            val binder: LocationUpdatesService.LocalBinder =
//                service as LocationUpdatesService.LocalBinder
//            mService = binder.service
//            mBound = true
//            if (checkPermissions()) {
//                showEnableLocationSetting()
//            }
//        }
//
//        override fun onServiceDisconnected(name: ComponentName) {
//            mService = null
//            mBound = false
//        }
//    }
//
//    /**
//     * Returns the current state of the permissions needed.
//     */
//    private fun checkPermissions(): Boolean {
//        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
//            this,
//            Manifest.permission.ACCESS_FINE_LOCATION
//        )
//    }
//
//    private fun requestPermissions() {
//        val shouldProvideRationale =
//            ActivityCompat.shouldShowRequestPermissionRationale(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            )
//
//        // Provide an additional rationale to the user. This would happen if the user denied the
//        // request previously, but didn't check the "Don't ask again" checkbox.
//        if (shouldProvideRationale) {
//            AppLogger.info("Displaying permission rationale to provide additional context.")
//            askPermission()
//
//        } else {
//            AppLogger.info("Requesting permission")
//            // Request permission. It's possible this can be auto answered if device policy
//            // sets the permission in a given state or the user denied the permission
//            // previously and checked "Never ask again".
//            askPermission()
//        }
//    }
//
//    private fun askPermission() {
//        ActivityCompat.requestPermissions(
//            this,
//            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//            INTENT_KEY_LOCATION_PERMISSION_REQUEST
//        )
//    }
//
//    /**
//     * Callback received when a permissions request has been completed.
//     */
//    override fun onRequestPermissionsResult(
//        requestCode: Int, permissions: Array<String?>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        AppLogger.info("onRequestPermissionResult")
//        if (requestCode == INTENT_KEY_LOCATION_PERMISSION_REQUEST) {
//            when {
//                grantResults.isEmpty() -> {
//                    // If user interaction was interrupted, the permission request is cancelled and you
//                    // receive empty arrays.
//                    AppLogger.info("User interaction was cancelled.")
//                }
//                grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
//                    // Permission was granted.
//                    showEnableLocationSetting()
//                }
//                else -> {
//                    // Permission denied.
//                    MaterialAlertDialogBuilder(
//                        this,
//                        R.style.AlertConfirmationDialog
//                    ).setTitle(getString(R.string.permission_rationale_title))
//                        .setMessage(getString(R.string.permission_rationale_msg))
//                        .setPositiveButton(
//                            getString(R.string.txt_take_me_setting)
//                        ) { p0, _ ->
//                            p0.dismiss()
//                            val intent = Intent()
//                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//                            val uri = Uri.fromParts(
//                                "package",
//                                BuildConfig.APPLICATION_ID, null
//                            )
//                            intent.data = uri
//                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                            startActivity(intent)
//                        }.setNegativeButton(
//                            getString(R.string.str_cancel)
//                        ) { p0, _ ->
//                            p0.dismiss()
//                            finish()
//                        }.show()
//                }
//            }
//        }
//    }
//
//    fun showEnableLocationSetting() {
//        let {
//            val locationRequest = LocationRequest.create()
//            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//
//            val builder = LocationSettingsRequest.Builder()
//                .addLocationRequest(locationRequest)
//
//            val task = LocationServices.getSettingsClient(it)
//                .checkLocationSettings(builder.build())
//
//            task.addOnSuccessListener {
////                val states = response.locationSettingsStates
////                if (states.isLocationPresent) {
////                    mService!!.requestLocationUpdates()
////                }
//                isLocationDialogIsOpen = true
//
//                mService!!.requestLocationUpdates()
//            }
//            task.addOnFailureListener { e ->
//                when ((e as ApiException).statusCode) {
//                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> try {
//                        isLocationDialogIsOpen = true
//                        // Show the dialog by calling startResolutionForResult(), and check the
//                        // result in onActivityResult().
//                        val rae = e as ResolvableApiException
//                        rae.startResolutionForResult(
//                            this,
//                            INTENT_KEY_GPS_PERMISSION_REQUEST
//                        )
//                    } catch (sie: IntentSender.SendIntentException) {
//                        sie.printStackTrace()
//                    }
//                    else -> finish()
//                }
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == INTENT_KEY_GPS_PERMISSION_REQUEST) {
//            if (resultCode == Activity.RESULT_OK) {
//                isLocationDialogIsOpen = false
//                mService!!.requestLocationUpdates()
//            } else {
//                isLocationDialogIsOpen = false
//                finish()
//            }
//        }
//    }
//
//    private fun registerGpsOnOffReceiver() {
//        registerReceiver(GPSCheck(object : GPSCheck.LocationCallBack {
//            override fun turnedOn() {
//                AppLogger.debug("GpsReceiver is turned on")
//                isLocationDialogIsOpen = false
//            }
//
//            override fun turnedOff() {
//                AppLogger.debug("GpsReceiver is turned off")
//                showEnableLocationSetting()
//            }
//        }), IntentFilter(LocationManager.MODE_CHANGED_ACTION))
//    }
}