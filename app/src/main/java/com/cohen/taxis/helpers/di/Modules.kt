package com.cohen.taxis.helpers.di

import com.cohen.taxis.helpers.room.Persistent
import com.cohen.taxis.viewmodel.TaxiViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module(override = true) {
    single { Persistent(androidContext()) }
}
var viewModelModule = module {
    viewModel {
        TaxiViewModel(get())
    }
}
