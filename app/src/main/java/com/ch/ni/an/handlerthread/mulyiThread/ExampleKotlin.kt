package com.ch.ni.an.handlerthread.mulyiThread


import android.widget.TextView


typealias updateText1 = (i :Int) -> Unit

class Example(
    private val textView :TextView,
) {

    var count = 100

    @Synchronized
    fun doWork() {

        Thread.sleep(3000)
        textView.post {
            updateText(count)
            count += 100
        }
        Thread.sleep(1000)
        textView.post {
            updateText(1000)
        }
    }


    val updateText: updateText1 = {
        textView.text = it.toString()
    }


}


