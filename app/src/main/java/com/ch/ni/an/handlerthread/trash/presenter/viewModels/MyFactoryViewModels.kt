package com.ch.ni.an.handlerthread.trash.presenter.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ch.ni.an.handlerthread.trash.domain.FetchAny
import java.lang.IllegalStateException

class MyFactoryViewModels(
   private val fetchAny :FetchAny
): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass :Class<T>) :T {

       if(modelClass.isAssignableFrom(OkHttpViewModel::class.java)){
           return OkHttpViewModel(fetchAny) as T
       }
        throw IllegalStateException("Unchecked cast")
    }
}