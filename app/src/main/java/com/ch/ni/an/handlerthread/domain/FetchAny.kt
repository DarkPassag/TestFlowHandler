package com.ch.ni.an.handlerthread.domain


import android.util.Log
import com.ch.ni.an.handlerthread.model.okHttp.CancelFlow
import com.ch.ni.an.handlerthread.model.okHttp.GetOkHttp
import com.ch.ni.an.handlerthread.model.okHttp.OkHttp
import com.ch.ni.an.handlerthread.model.retrofit.Common
import com.ch.ni.an.handlerthread.model.retrofit.GetData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import kotlin.system.measureTimeMillis


class FetchAny: GetOkHttp, CancelFlow, GetData {

    private val okHttp = OkHttp.response

    private val service = Common.retrofit

    private var countRequest = 0

    private var someBoolean = true


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
        return okHttp.clone().execute().use {
            if (!it.isSuccessful) throw IOException(it.message)
            it.body!!.string()
        }
    }

    override suspend fun getOkHttp() :Flow<String> = flow {
        while (someBoolean) {
            delay(5000)
            countRequest++
            Log.e("Tag", "Request №: $countRequest")
            val someText = okHttp.clone().execute().body!!.string()
            emit(someText)
        }

    }.flowOn(Dispatchers.IO)

    override fun cancelFlow(status :Boolean) {
        someBoolean = status
    }

    override suspend fun getText() {
        service.fetchAny().also {
            if (!it.isSuccessful) throw IOException(it.message())

        }
    }

    override suspend fun getTwiceText() :String {

        val text = coroutineScope {
            val firstText = async {
                simpleText() }
            val secondText = async { simpleText() }

            return@coroutineScope "${firstText.await()}\n\n${secondText.await()}"
        }

        return text
    }



    private suspend fun simpleText() :String {
        return service.fetchAny().let {
            if (!it.isSuccessful) throw IOException(it.message())
            it.body().toString()
        }

    }


   suspend fun testingRequest(){
        val time1 = measureTimeMillis {
            coroutineScope {
                var text = ""
               repeat(100){
                   text = async { simpleText() }.await()
               }
            }
        }
       val time2 = measureTimeMillis {
           var text = ""
           for(i in 0..100){
               text = simpleText()
           }
       }
       Log.e("Async", "AsyncTimeRequest: $time1")
       Log.e("Sync", "SyncTimeRequest: $time2")
    }
}







