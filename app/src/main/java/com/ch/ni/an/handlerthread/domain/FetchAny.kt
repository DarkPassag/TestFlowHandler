package com.ch.ni.an.handlerthread.domain

import com.ch.ni.an.handlerthread.model.okHttp.GetOkHttp
import com.ch.ni.an.handlerthread.model.okHttp.OkHttp
import java.io.IOException


class FetchAny(): GetOkHttp {

    private val okHttp = OkHttp.response

    /*
    fun getTextOkHttp() : MutableLiveData<String> {

        val responseLiveData = MutableLiveData<String>()
        okHttp.enqueue(object :Callback{
            override fun onFailure(call :Call, e :IOException) {
                Log.e("Tag", "SomeTrouble")
            }

            override fun onResponse(call :Call, response :Response) {
                if(!response.isSuccessful) throw IOException("Unchecked response")
                responseLiveData.postValue(response.body!!.string())
            }

        })
        return responseLiveData
    }
    */


    override fun getOkhttp() :String {
        okHttp.execute().use {
            if(!it.isSuccessful) throw IOException(it.message)
            return it.body!!.string()
        }
    }


}


