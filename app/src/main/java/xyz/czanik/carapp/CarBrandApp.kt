package xyz.czanik.carapp

import android.app.Application

class CarBrandApp : Application(), Container {

    override lateinit var navigator: Navigator
    override lateinit var repositoryFactory: RepositoryFactory

    override fun onCreate() {
        super.onCreate()
        navigator = StubNavigator()
        repositoryFactory = StubRepositoryFactory()
    }
}

