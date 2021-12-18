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


    private val _newPost : MutableLiveData<PostModel> by lazy {
        MutableLiveData<PostModel>()
    }

    val newPost: LiveData<PostModel> get() {
       return _newPost
    }

    private val _patchPost = MutableLiveData<PostModel>()
    val patchPost: LiveData<PostModel> get() = _patchPost



    fun getPosts(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            _posts.postValue(repository.getPosts(id))
        }
    }

    fun addPost(postModel: PostModel){
        viewModelScope.launch(Dispatchers.IO){
            _newPost.postValue(repository.updatePost(postModel))
        }
    }

    fun patchPost(id: Int, title:String){
        viewModelScope.launch(Dispatchers.IO){
            _patchPost.postValue(repository.patchPost(id, title))

        }

    }
}