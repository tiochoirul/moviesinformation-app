package com.tcm.moviesinformationapp

import android.app.Application
import com.tcm.core.di.databaseModule
import com.tcm.core.di.networkModule
import com.tcm.core.di.repositoryModule
import com.tcm.moviesinformationapp.di.sessionManagerModule
import com.tcm.moviesinformationapp.di.useCaseModule
import com.tcm.moviesinformationapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    sessionManagerModule,
                    viewModelModule
                )
            )
        }
    }
}