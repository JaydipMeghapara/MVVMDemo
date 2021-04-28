package com.jaydip.mvvmdemo.di

import android.app.Application
import com.jaydip.mvvmdemo.db.AppDataStore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Created by Mayur Solanki on 29/09/20, 12:35 PM.
 */


val preferenceModule = module {
    single {
        provideSharedPreference(androidApplication())
    }
}

fun provideSharedPreference(application: Application) : AppDataStore {
      return  AppDataStore(application)
}