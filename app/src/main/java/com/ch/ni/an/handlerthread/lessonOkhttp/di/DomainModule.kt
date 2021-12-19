package com.ch.ni.an.handlerthread.lessonOkhttp.di


import domain.FetchListUsers
import org.koin.dsl.module

val domainModule = module {

    factory {
        FetchListUsers(get())
    }

    factory {

    }
}