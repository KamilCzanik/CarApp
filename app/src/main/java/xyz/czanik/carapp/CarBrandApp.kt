package xyz.czanik.carapp

import android.app.Application

class CarBrandApp : Application(), Container {

    override lateinit var navigator: Navigator

    override fun onCreate() {
        super.onCreate()
        navigator = StubNavigator()
    }
}

