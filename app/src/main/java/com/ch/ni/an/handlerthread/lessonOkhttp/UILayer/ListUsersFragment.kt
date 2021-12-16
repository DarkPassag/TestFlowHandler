package com.ch.ni.an.handlerthread.lessonOkhttp.UILayer


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.R
import com.ch.ni.an.handlerthread.databinding.FragmentUsersBinding
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User
import com.ch.ni.an.handlerthread.lessonOkhttp.UILayer.adapters.UsersAdapter
import com.ch.ni.an.handlerthread.lessonOkhttp.UILayer.viewModels.ListUserViewModel


class ListUsersFragment : Fragment() {

    private var _binding :FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val myModel :ListUserViewModel by viewModels()
    private lateinit var recyclerView :RecyclerView
    private lateinit var adapter :UsersAdapter

    override fun onCreateView(
        inflater :LayoutInflater,
        container :ViewGroup?,
        savedInstanceState :Bundle?,
    ) :View? {

        _binding = FragmentUsersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view :View, savedInstanceState :Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        adapter = UsersAdapter {
            val user :User = it.tag as User
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                val bundle = bundleOf(KEY_USER to user)
                addToBackStack(null)
                replace<UserFragment>(R.id.fragmentContainer, args = bundle)
            }
        }
        recyclerView.adapter = adapter

        myModel.users.observe(this, {
            adapter.submitList(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showUserDetail(){
        parentFragmentManager.commit {  }
    }
}

const val KEY_USER = "keySomeUser"