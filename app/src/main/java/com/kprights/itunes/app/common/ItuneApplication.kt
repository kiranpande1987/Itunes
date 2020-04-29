package com.kprights.itunes.app.common

import android.app.Application
import timber.log.Timber


/**
 * Copyright (c) 2020 for KPrights
 *
 * User : Kiran Pande
 * Date : 29/04/20
 * Time : 9:39 PM
 */

class ItuneApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}