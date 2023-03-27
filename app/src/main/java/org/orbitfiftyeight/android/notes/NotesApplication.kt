package org.orbitfiftyeight.android.notes

import android.app.Application
import org.orbitfiftyeight.android.notes.dependencyinjection.DependencyInjector

/**
 * Application class responsible for initializing the dependency injector.
 */
class NotesApplication : Application() {

    lateinit var dependencyInjector: DependencyInjector

    override fun onCreate() {
        super.onCreate()
        initDependencyInjector()
    }

    private fun initDependencyInjector() {
        dependencyInjector = DependencyInjector(this)
    }
}
