package com.ch.ni.an.handlerthread



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.databinding.ActivityMainBinding
import com.ch.ni.an.handlerthread.domain.FetchAny
import com.ch.ni.an.handlerthread.presenter.PostAdapter
import com.ch.ni.an.handlerthread.presenter.viewModels.MyFactoryViewModels
import com.ch.ni.an.handlerthread.presenter.viewModels.OkHttpViewModel



class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding

    private lateinit var myModel: OkHttpViewModel
    private lateinit var factory: MyFactoryViewModels

    private lateinit var recyclerView:RecyclerView
    private lateinit var adapter: PostAdapter


    override fun onCreate(savedInstanceState :Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fetchAny = FetchAny()
        factory = MyFactoryViewModels(fetchAny)


        myModel = ViewModelProvider(this,
        factory).get(OkHttpViewModel::class.java)

        adapter = PostAdapter()
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter

        myModel.post.observe(this, {
            adapter.submitList(it)
        })





//        lifecycleScope.launchWhenResumed {
//            myModel.someSting.onEach {
//                binding.textView.text = it
//            }.collect()
//        }

//        myModel.message.observe(this,{
//            binding.retrofitTextView.text = it
//        })


        binding.liveDataButton.setOnClickListener {
            createRequestWithOkhttp()
        }

//        binding.bottomButtonsView.setListener {
//            if(it == BottomButtonAction.POSITIVE){
//                startActivity(Intent(this, TicTacActivity::class.java))
//                finish()
//
//            }
//        }




    }

    private fun createRequestWithOkhttp(){
        myModel.fetchText()
    }

    private fun createRequestWithFlow(){
        myModel.getSomeString()
        startStopFlow(true)
    }

    private fun startStopFlow(status: Boolean){
        myModel.startStopFlow(status)
    }







}