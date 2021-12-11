package com.ch.ni.an.handlerthread


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.ch.ni.an.handlerthread.domain.FetchAny
import com.ch.ni.an.handlerthread.presenter.viewModels.MyFactoryViewModels
import com.ch.ni.an.handlerthread.presenter.viewModels.OkHttpViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var textView :TextView
    private lateinit var myModel: OkHttpViewModel
    private lateinit var factory: MyFactoryViewModels

    override fun onCreate(savedInstanceState :Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fetchAny = FetchAny()
        factory = MyFactoryViewModels(fetchAny)

        myModel = ViewModelProvider(this,
        factory).get(OkHttpViewModel::class.java)

        textView = findViewById(R.id.textView)

        myModel.someText.observe(this, {
            textView.text = it
        })


    }







}