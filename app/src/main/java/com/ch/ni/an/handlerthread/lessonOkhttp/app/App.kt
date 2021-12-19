package com.ch.ni.an.handlerthread.lessonOkhttp.app

import android.app.Application
import com.ch.ni.an.handlerthread.lessonOkhttp.di.appModule
import com.ch.ni.an.handlerthread.lessonOkhttp.di.dataModule
import com.ch.ni.an.handlerthread.lessonOkhttp.di.domainModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}