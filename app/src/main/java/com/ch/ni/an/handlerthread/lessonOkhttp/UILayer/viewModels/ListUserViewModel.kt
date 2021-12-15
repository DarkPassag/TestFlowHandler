package com.ch.ni.an.handlerthread.lessonOkhttp.UILayer.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User
import com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListUserViewModel: ViewModel() {

    private val repository = Repository()


    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users


    init {

        viewModelScope.launch(Dispatchers.IO){
            val listUsers = repository.fetchListOfUsers()
            _users.postValue(listUsers)
        }

    }
}