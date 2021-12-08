package com.ch.ni.an.handlerthread


import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ch.ni.an.handlerthread.retrofit.Common
import com.ch.ni.an.handlerthread.retrofit.RetrofitService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var textView :TextView
    private lateinit var handlerTest: HandlerTests
    private lateinit var service :RetrofitService


    override fun onCreate(savedInstanceState :Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
        startService(Intent(this, TestService::class.java))
        finish()
        textView = findViewById(R.id.textView)
        */

        handlerTest = HandlerTests()
        textView = findViewById(R.id.textView)


        val button: Button = findViewById(R.id.testButton)
        val startButton = findViewById<Button>(R.id.startButton)
        val stopButton = findViewById<Button>(R.id.stopButton)
        val myModel = ViewModelProvider(this).get(Corutines::class.java)
        service = Common.retrofit

        myModel.setText2(textView)

        setTextFlow()



    }

    private fun setTextFlow() = CoroutineScope(Dispatchers.Default).launch {


        val flow: Flow<String> = flow {
            while (true){
                val a = service.fetchAny().body()
                emit(a.toString())
            }
        }

        while (true){
            flow
               .collect { someQuote ->
                   launch(Dispatchers.Main) {
                       textView.text = someQuote
                   }

                }
        }

    }

    val something: (Int, Int) -> Int =







        override fun onResume() {
        super.onResume()
        Log.e("q", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("q", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("q", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("q", "onDestroy")
    }





}