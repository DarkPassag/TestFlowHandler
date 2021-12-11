package com.ch.ni.an.handlerthread


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ch.ni.an.handlerthread.databinding.ActivityMainBinding
import com.ch.ni.an.handlerthread.domain.FetchAny
import com.ch.ni.an.handlerthread.presenter.viewModels.MyFactoryViewModels
import com.ch.ni.an.handlerthread.presenter.viewModels.OkHttpViewModel
import kotlinx.coroutines.flow.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding

    private lateinit var myModel: OkHttpViewModel
    private lateinit var factory: MyFactoryViewModels

    override fun onCreate(savedInstanceState :Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fetchAny = FetchAny()
        factory = MyFactoryViewModels(fetchAny)


        myModel = ViewModelProvider(this,
        factory).get(OkHttpViewModel::class.java)


        myModel.someText.observe(this, {
            binding.textView.text = it
        })


        lifecycleScope.launchWhenResumed {
            myModel.someSting.onEach {
                binding.textView.text = it
            }.collect()
        }

        myModel.message.observe(this,{
            binding.retrofitTextView.text = it
        })


        binding.liveDataButton.setOnClickListener {
            createRequestWithOkhttp()
        }
        binding.flowButton.setOnClickListener {
            createRequestWithFlow()
            startStopFlow(true)
        }

        binding.stopFlowButton.setOnClickListener {
                startStopFlow(false)
        }

    }

    private fun createRequestWithOkhttp(){
        myModel.fetchText()
    }

    private fun createRequestWithFlow(){
        myModel.getSomeString()
    }

    private fun startStopFlow(status: Boolean){
        myModel.startStopFlow(status)
    }







}