package com.example.githubrepoexplorer

import android.app.Application
import com.example.githubrepoexplorer.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class RepoExplorerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RepoExplorerApplication)
            modules(appModule)
            Timber.plant(Timber.DebugTree())
        }
    }
}