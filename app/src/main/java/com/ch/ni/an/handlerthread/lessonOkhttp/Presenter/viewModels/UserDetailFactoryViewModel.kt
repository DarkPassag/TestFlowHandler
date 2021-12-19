package com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.okHttp.OkHttp
import data.repository.RepositoryImpl
import data.retrofit.Common


class UserDetailFactoryViewModel: ViewModelProvider.Factory {

    private val userRepositoryImpl: RepositoryImpl by lazy {
        RepositoryImpl(
            okHttp = OkHttp,
            service = Common.service
        )
    }




    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userRepositoryImpl) as T
    }
}