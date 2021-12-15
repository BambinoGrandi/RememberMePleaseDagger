package ru.grandibambino.remembermeplease

import android.app.Application
import android.content.Context
import ru.grandibambino.remembermeplease.core.di.AppComponent
import ru.grandibambino.remembermeplease.core.di.DaggerAppComponent

class RememberMePleaseApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is RememberMePleaseApp -> appComponent
        else -> applicationContext.appComponent
    }
