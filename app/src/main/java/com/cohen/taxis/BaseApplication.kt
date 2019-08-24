package com.cohen.taxis

import android.app.Application
import com.cohen.taxis.helpers.di.applicationModule
import com.cohen.taxis.helpers.di.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Android context
            androidContext(this@BaseApplication)
            // modules
            modules(applicationModule)
            modules(viewModelModule)


        }
    }
}