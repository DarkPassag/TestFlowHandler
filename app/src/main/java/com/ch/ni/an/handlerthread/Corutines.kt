package com.ch.ni.an.handlerthread


import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ch.ni.an.handlerthread.retrofit.Common
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow


class Corutines: ViewModel() {

    val retrofit = Common.retrofit


    fun setText(textView :TextView ){
        viewModelScope.launch {
            delay(1000)
            val result = withContext(Dispatchers.IO){

                val part1 = retrofit.fetchAny().body()
                Log.e("launch", "firstPart")
                delay(2000)
                val part2 =  retrofit.fetchAny().body()
                Log.e("launch", "second")
                delay(1000)
                val part3 = retrofit.fetchAny().body()
                Log.e("launch", "tree")


                return@withContext "\n-------------" +
                        "\n$part1\n-------------" +
                        "\n$part2\n-------------" +
                        "\n$part3\n-------------"
            }
            textView.text = result
        }
    }
    fun setText2(textView :TextView) {
        viewModelScope.launch {

            val result = withContext(Dispatchers.IO) {

                val part1 = async { retrofit.fetchAny().body() }
                val part2 = async { retrofit.fetchAny().body() }
                val part3 = async { retrofit.fetchAny().body() }

                return@withContext "\n-------------" +
                        "\n${part1.await()}\n-------------" +
                        "\n${part2.await()}\n-------------" +
                        "\n${part3.await()}\n-------------"
            }
            textView.text = result
        }
        textView.text = "awaiting"

    }
















}