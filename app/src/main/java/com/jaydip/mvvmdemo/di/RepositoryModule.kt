package com.jaydip.mvvmdemo.di


import com.jaydip.mvvmdemo.api.ApiHelper
import com.jaydip.mvvmdemo.repository.MainRepository
import com.jaydip.mvvmdemo.util.NetworkHelper
import org.koin.dsl.module


val repoModule = module {

    single {
        provideUserRepository(get(), get())
    }

//    single {
//        SignUpRepository(get())
//    }

}

fun provideUserRepository(
    apiHelper: ApiHelper,
    networkHelper: NetworkHelper
): MainRepository {
    return MainRepository(apiHelper,  networkHelper)
}

//
//
//fun SignUpRepository(apiHelper: ApiHelper): SignUpRepository {
//    return SignUpRepository(apiHelper)
//}

