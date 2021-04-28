package com.jaydip.mvvmdemo.di

import android.content.Context
import com.jaydip.mvvmdemo.BuildConfig
import com.jaydip.mvvmdemo.api.ApiHelper
import com.jaydip.mvvmdemo.api.ApiHelperImpl
import com.jaydip.mvvmdemo.api.ApiService
import com.jaydip.mvvmdemo.model.PrefGlobal.PREF_API_SERVER
import com.jaydip.mvvmdemo.util.AppConstant.PREF_DEV_SERVER
import com.jaydip.mvvmdemo.util.AppConstant.PREF_QA_SERVER
import com.jaydip.mvvmdemo.util.AppConstant.PREF_UAT_SERVER
import com.jaydip.mvvmdemo.util.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {

    single {
        provideNetworkHelper(androidContext())
    }

    single {
        provideOkHttpClient()
    }

    single {
        when (PREF_API_SERVER) {
            PREF_DEV_SERVER -> {
                provideRetrofit(get(), BuildConfig.BASE_URL_DEV)
            }
            PREF_QA_SERVER -> {
                provideRetrofit(get(), BuildConfig.BASE_URL_QA)
            }
            PREF_UAT_SERVER -> {
                provideRetrofit(get(), BuildConfig.BASE_URL_UAT)
            }
            else -> {
                provideRetrofit(get(), BuildConfig.BASE_URL_UAT)
            }

        }

    }

    single {
        provideApiService(get())
    }


    single {
//           ApiHelperImpl(get())
        provideApiHelper(get())
    }


}

private fun provideNetworkHelper(context: Context): NetworkHelper {
    return NetworkHelper(context)
}

private fun provideOkHttpClient(): OkHttpClient {

    var okHttpClient: OkHttpClient? = null

    okHttpClient = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder().addInterceptor(loggingInterceptor).readTimeout(
            30,
            TimeUnit.SECONDS
        ).connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30,
            TimeUnit.SECONDS).retryOnConnectionFailure(true).build()
    } else {
        OkHttpClient.Builder().readTimeout(
            30,
            TimeUnit.SECONDS
        ).connectTimeout(30, TimeUnit.SECONDS).writeTimeout(30,
            TimeUnit.SECONDS).retryOnConnectionFailure(true).build()
    }

    return okHttpClient
}

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

private fun provideApiHelper(apiService: ApiService): ApiHelper {
    return ApiHelperImpl(apiService)
}




