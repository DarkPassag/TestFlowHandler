package com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.ni.an.handlerthread.lessonOkhttp.Extensions.toPostDomainModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Extensions.toPostUiModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.models.PostUiModel
import domain.repository.FetchPostsById
import domain.repository.PatchPost
import domain.repository.PostsRepository
import domain.repository.UserRepository


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(
   private val postsRepository: PostsRepository
) : ViewModel() {



    private val _posts = MutableLiveData<List<PostUiModel>>()
    val post: LiveData<List<PostUiModel>>
        get() = _posts


    private val _newPost: MutableLiveData<PostUiModel> by lazy {
        MutableLiveData<PostUiModel>()
    }

    val newPost: LiveData<PostUiModel>
        get() {
            return _newPost
        }

    private val _patchPosts = MutableLiveData<PostUiModel>()
    val patchPosts: LiveData<PostUiModel> get() = _patchPosts


    fun getPosts(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
             _posts.postValue(postsRepository.getPosts(id).map { it.toPostUiModel() })
        }
    }

    fun addPost(postModel: PostUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _newPost.postValue(postsRepository.newPost(postModel.toPostDomainModel()).toPostUiModel())
        }
    }

    fun patchPost(id: Int, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _patchPosts.postValue(postsRepository.patchPost(id, title).toPostUiModel())

        }

    }
}