package com.ch.ni.an.handlerthread.lessonOkhttp.di

import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels.ListUserViewModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    viewModel {
        ListUserViewModel(get())
    }

    viewModel {
        UserViewModel(get())
    }
}