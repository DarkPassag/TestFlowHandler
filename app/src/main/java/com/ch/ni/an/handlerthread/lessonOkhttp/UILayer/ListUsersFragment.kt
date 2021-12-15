package com.ch.ni.an.handlerthread.lessonOkhttp.UILayer


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.R
import com.ch.ni.an.handlerthread.databinding.FragmentUsersBinding
import com.ch.ni.an.handlerthread.lessonOkhttp.UILayer.adapters.UsersAdapter
import com.ch.ni.an.handlerthread.lessonOkhttp.UILayer.viewModels.ListUserViewModel


class ListUsersFragment: Fragment() {

    private var _binding : FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val myModel: ListUserViewModel by viewModels()
    private lateinit var recyclerView :RecyclerView
    private lateinit var adapter :UsersAdapter

    private val fab by lazy {
        binding.fab
    }

    override fun onCreateView(
        inflater :LayoutInflater,
        container :ViewGroup?,
        savedInstanceState :Bundle?) :View? {

        _binding = FragmentUsersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view :View, savedInstanceState :Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        adapter = UsersAdapter {
            //TODO
        }
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView :RecyclerView, dx :Int, dy :Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(dy > 10 && fab.isShown ){
                    fab.hide()
                }
                if (dy < -10 && !fab.isShown) {
                    fab.show()
                }
                if (dy < -10 && !fab.isShown) {
                    fab.show()
                }
            }
        })



        myModel.users.observe(this, {
            adapter.submitList(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}