package com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.retrofit.Common
import data.repository.RepositoryImpl
import data.okHttp.OkHttp
import domain.FetchListUsers

class ViewModelsFactory(

) : ViewModelProvider.Factory {

    private val repositoryImpl: RepositoryImpl by lazy {
        RepositoryImpl(
            okHttp = OkHttp,
            service = Common.service
        )
    }

    private val fetchListUsers: FetchListUsers by lazy {
        FetchListUsers(
            userRepository = repositoryImpl
        )
    }



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListUserViewModel(fetchListUsers) as T
    }
}