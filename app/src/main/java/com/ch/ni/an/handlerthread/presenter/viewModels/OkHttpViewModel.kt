package com.ch.ni.an.handlerthread.presenter.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.ni.an.handlerthread.domain.FetchAny
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OkHttpViewModel(
   private val fetchAny :FetchAny
): ViewModel() {

    private val _someText = MutableLiveData<String>()
    val someText: LiveData<String> = _someText



    /*
    fun fetchText(){
        viewModelScope.launch(Dispatchers.IO) {
            val tempText = fetchAny.getTextOkHttp()
            _someText.postValue(tempText)
        }
    }
     */

   private fun fetchText(){
        viewModelScope.launch(Dispatchers.IO) {
           _someText.postValue(fetchAny.getOkhttp())
        }
    }

    init {
        fetchText()
    }
}