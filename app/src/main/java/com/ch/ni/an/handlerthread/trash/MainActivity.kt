package com.ch.ni.an.handlerthread.trash



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.databinding.ActivityMainBinding
import com.ch.ni.an.handlerthread.trash.domain.FetchAny
import com.ch.ni.an.handlerthread.trash.model.HeadClassModel
import com.ch.ni.an.handlerthread.trash.presenter.adapters.HeaderAdapter
import com.ch.ni.an.handlerthread.trash.presenter.adapters.PostAdapter
import com.ch.ni.an.handlerthread.trash.presenter.viewModels.MyFactoryViewModels
import com.ch.ni.an.handlerthread.trash.presenter.viewModels.OkHttpViewModel



class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding

    private lateinit var myModel: OkHttpViewModel
    private lateinit var factory: MyFactoryViewModels

    private lateinit var recyclerView:RecyclerView
    private lateinit var adapter:PostAdapter
    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var concatAdapter :ConcatAdapter


    override fun onCreate(savedInstanceState :Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fetchAny = FetchAny()
        factory = MyFactoryViewModels(fetchAny)


        myModel = ViewModelProvider(this,
        factory).get(OkHttpViewModel::class.java)


        headerAdapter = HeaderAdapter {
            Toast.makeText(this, " ${it.tag}", Toast.LENGTH_SHORT).show()
        }
        adapter = PostAdapter()
        concatAdapter = ConcatAdapter(headerAdapter, adapter)
        recyclerView = binding.recyclerView
        recyclerView.adapter = concatAdapter



        myModel.post.observe(this, {
            it?.let {
                headerAdapter.submitList(
                    listOf(HeadClassModel("Count of posts: ${it.size}")))
            }
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