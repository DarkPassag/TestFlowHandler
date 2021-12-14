package com.ch.ni.an.handlerthread.domain




import android.util.Log
import com.ch.ni.an.handlerthread.model.*
import com.ch.ni.an.handlerthread.model.okHttp.CancelFlow
import com.ch.ni.an.handlerthread.model.okHttp.GetListPosts
import com.ch.ni.an.handlerthread.model.okHttp.GetOkHttp
import com.ch.ni.an.handlerthread.model.okHttp.OkHttp
import com.ch.ni.an.handlerthread.model.retrofit.Common
import com.ch.ni.an.handlerthread.model.retrofit.GetData
import com.ch.ni.an.handlerthread.model.retrofit.GetUsers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException
import java.lang.IllegalStateException




class FetchAny: GetOkHttp, CancelFlow, GetData, GetListPosts, GetUsers {

    private val okHttp = OkHttp

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
        return okHttp.response.clone().execute().use {
            if (!it.isSuccessful) throw IOException(it.message)
            it.body!!.string()
        }
    }

    override suspend fun getOkHttp() :Flow<String> = flow {
        while (someBoolean) {
            delay(5000)
            countRequest++
            Log.e("Tag", "Request №: $countRequest")
            val someText = okHttp.response.clone().execute().body!!.string()
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
               }
            val secondText = async {  }

            return@coroutineScope "${firstText.await()}\n\n${secondText.await()}"
        }

        return text
    }



    private suspend fun simpleTextRetrofit() :String {
        return service.fetchAny().let {
            if (!it.isSuccessful) throw IOException(it.message())
            it.body().toString()
        }
    }

    private fun simpleTextOkHttp() :String {
        return okHttp.response.execute().use {
            if (!it.isSuccessful) throw IllegalStateException("")
            it.body!!.string()
        }
    }

    override suspend fun getListPosts() :List<Post> {
        okHttp.response.clone().execute().use {
            if (!it.isSuccessful) throw IllegalStateException(it.message)
            val response = it.body!!.string()
            val listPosts = Json.decodeFromString<List<PostModel>>(response).map { it.toPost() }
            return listPosts
        }

    }

    override fun getListUsers() :List<User> {
        okHttp.responseUsers.clone().execute().use {
            if(!it.isSuccessful && it.body!!.string().isEmpty()) throw IOException(it.message)
            val response = it.body!!.string()

            val format = Json { ignoreUnknownKeys = true}
            val listUsers = format.decodeFromString<List<UserModel>>(response).apply {
                forEach {
                    Log.e("SomeUser", it.username)
                }
            }
            return emptyList()
            }
        }
    }




    /**
   suspend fun testingRequest(){
        val time1 = measureTimeMillis {
            coroutineScope {
                var text = ""
                repeat(100){
                    async { simpleTextRetrofit() }
                }
                
            }
        }
       val time2 = measureTimeMillis {
           var text = ""
           for(i in 0..100){
               text = simpleTextRetrofit()
           }
       }

       val time3 = measureTimeMillis {
           var text = ""
           repeat(100){
               text = simpleTextOkHttp()
           }
       }
       Log.e("Async", "AsyncTimeRequest: $time1")
       Log.e("Sync", "SyncTimeRequest: $time2")
       Log.e("SyncOkHttp", "SyncTimeRequestOkhttp: $time3")
    }

   */








