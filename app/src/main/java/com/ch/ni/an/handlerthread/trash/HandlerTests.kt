package com.ch.ni.an.handlerthread.trash

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import kotlin.random.Random

class HandlerTests {



companion object {

    val someArray = IntArray(100)
}

    private val handler = Handler(Looper.getMainLooper())


    fun writeToArray(textView :TextView) {
        Thread {
            repeat(10) {
                Thread.sleep(1000)
                someArray[0] = Random.nextInt(1, 11)
                handler.post { textView.text = someArray[0].toString() }
                val a = Thread.currentThread().name
                Log.e("a", a.toString())
            }

        }.start()



    }

    fun writeToArrayTwo(){
            Thread {
                repeat(10) {
                    Thread.sleep(1500)
                    someArray[0] = Random.nextInt(100, 200)

                }
            }.start()



    }


}



