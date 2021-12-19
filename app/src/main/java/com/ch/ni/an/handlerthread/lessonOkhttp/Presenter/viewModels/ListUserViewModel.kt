package com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.ni.an.handlerthread.lessonOkhttp.Extensions.toUserUiModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.models.UserUiModel
import domain.FetchListUsers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListUserViewModel(
   private val fetchListUsers: FetchListUsers
): ViewModel() {


    private val _users = MutableLiveData<List<UserUiModel>>()
    val users: LiveData<List<UserUiModel>>
        get() = _users


    init {

        viewModelScope.launch(Dispatchers.IO){
            val listUsers = fetchListUsers.fetchUsers()
            _users.postValue(
                listUsers.map { it.toUserUiModel() }
            )
        }

        Log.e("Created", "ViewModel is Created")

    }
}

