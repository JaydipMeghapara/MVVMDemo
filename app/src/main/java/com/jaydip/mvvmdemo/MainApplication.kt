package com.jaydip.mvvmdemo

import android.app.Application
import android.content.Context
import androidx.navigation.NavController
import com.chibatching.kotpref.Kotpref
import com.jaydip.mvvmdemo.di.*
import com.jaydip.mvvmdemo.util.NetworkConnectivity
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class MainApplication : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            NetworkConnectivity.init(this)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    appModule,
                    repoModule,
                    databaseModule,
                    viewModelModule,
                    preferenceModule
                )
            )
        }


    }

    override fun onLowMemory() {
        super.onLowMemory()

    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

         var navController: NavController? = null
    }

}