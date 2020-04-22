package com.adwera.mvvmkotlin

import android.app.Application
import com.adwera.mvvmkotlin.ui.auth.AuthViewModelFactory
import com.adwera.mvvmkotlin.ui.home.profile.ProfileModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class MVVMApplication : Application(), KodeinAware {


    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))
//        bind() from singleton { NetworkConnectorInterceptor(instance()) }
//        bind() from singleton { MyApi(instance()) }
//        bind() from singleton { AppDatabase(instance()) }
//        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider {ProfileModelFactory(instance())}

    }

}