package com.ch.ni.an.handlerthread.lessonOkhttp.di


import data.okHttp.OkHttp
import data.repository.RepositoryImpl
import data.retrofit.Common
import data.retrofit.RetrofitApi
import domain.repository.Repository
import org.koin.dsl.module

val dataModule = module {

    single {
        OkHttp
    }

    single<RetrofitApi> {
        Common.service
    }

    single<Repository> {
        RepositoryImpl(
            okHttp = get(),
            service = get()
        )
    }


}