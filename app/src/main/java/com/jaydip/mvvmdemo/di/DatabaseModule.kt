package com.jaydip.mvvmdemo.di

import android.app.Application
import com.jaydip.mvvmdemo.db.UserDao
import com.jaydip.mvvmdemo.db.UserDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideUserDao(get()) }
}


fun provideDatabase(application: Application): UserDatabase {
    return UserDatabase.getDbInstance(application)

}

fun provideUserDao(database: UserDatabase): UserDao {
    return database.userDao
}

