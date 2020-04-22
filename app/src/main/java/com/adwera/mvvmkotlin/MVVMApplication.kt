package com.adwera.mvvmkotlin

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class MVVMApplication : Application(), KodeinAware {


    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
//        bind() from singleton { NetworkConnectorInterceptor(instance()) }
//        bind() from singleton { MyApi(instance()) }
//        bind() from singleton { AppDatabase(instance()) }
//        bind() from singleton { UserRepository(instance(), instance()) }
//        bind() from provider { AuthViewModelFactory(instance()) }
    }

}