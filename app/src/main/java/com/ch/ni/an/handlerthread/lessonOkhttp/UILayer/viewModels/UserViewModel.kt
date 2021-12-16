package com.ch.ni.an.handlerthread.lessonOkhttp.UILayer.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DomainLayer.Repository
import com.ch.ni.an.handlerthread.trash.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {


    val repository = Repository()

    private val _posts = MutableLiveData<List<PostModel>>()
    val post:LiveData<List<PostModel>>
        get() = _posts



    fun getPosts(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            _posts.postValue(repository.getPosts(id))
        }
    }
}