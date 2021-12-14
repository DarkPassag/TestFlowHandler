package com.ch.ni.an.handlerthread.presenter.viewModels
import android.util.Log
import androidx.constraintlayout.helper.widget.Flow
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.ni.an.handlerthread.domain.FetchAny
import com.ch.ni.an.handlerthread.model.Post
import com.ch.ni.an.handlerthread.model.okHttp.CancelFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class OkHttpViewModel(
   private val fetchAny :FetchAny
): ViewModel() {

    private val _someText = MutableLiveData<String>()
    val someText: LiveData<String> = _someText

    private val _someString = MutableStateFlow<String>("awaiting")
    val someSting = _someString

    private val _posts = MutableLiveData<List<Post>>()
    val post: LiveData<List<Post>> = _posts




    /*
    fun fetchText(){
        viewModelScope.launch(Dispatchers.IO) {
            val tempText = fetchAny.getTextOkHttp()
            _someText.postValue(tempText)
        }
    }
     */

   fun fetchText(){
        viewModelScope.launch(Dispatchers.IO) {
           _someText.postValue(fetchAny.getOkhttp())

        }
    }

    fun getSomeString(){
        viewModelScope.launch(Dispatchers.IO) {
            fetchAny.getOkHttp().collect {
                _someString.emit(it)
            }
        }
    }

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private fun getTwiceString(){
        viewModelScope.launch(Dispatchers.IO) {
            fetchAny.getTwiceText().also {
                _message.postValue(it)
            }
        }
    }

    private fun getListPosts(){
        viewModelScope.launch(Dispatchers.IO){
            _posts.postValue(fetchAny.getListPosts())
        }
    }





    init {
        fetchText()
        getTwiceString()
        viewModelScope.launch(Dispatchers.IO) {
            fetchAny.getListPosts()
            fetchAny.getListUsers()
        }
        getListPosts()

    }

   fun startStopFlow(status: Boolean){
       fetchAny.cancelFlow(status)
   }


}