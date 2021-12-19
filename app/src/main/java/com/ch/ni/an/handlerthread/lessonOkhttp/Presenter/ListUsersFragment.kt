package com.ch.ni.an.handlerthread.lessonOkhttp.Presenter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.R
import com.ch.ni.an.handlerthread.databinding.FragmentUsersBinding
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.adapters.UsersAdapter
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.models.UserUiModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels.ListUserViewModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels.ViewModelsFactory


class ListUsersFragment : Fragment() {

    private var _binding :FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private lateinit var  myModel :ListUserViewModel
    private lateinit var factoryModel: ViewModelsFactory
    private lateinit var recyclerView :RecyclerView
    private lateinit var adapter :UsersAdapter

    override fun onCreateView(
        inflater :LayoutInflater,
        container :ViewGroup?,
        savedInstanceState :Bundle?,
    ) :View? {

        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        factoryModel = ViewModelsFactory()
        myModel = ViewModelProvider(this, factoryModel).get(ListUserViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view :View, savedInstanceState :Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        adapter = UsersAdapter {
            val user :UserUiModel = it.tag as UserUiModel
            showUserDetail(user)
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

    private fun showUserDetail(user: UserUiModel){
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            val bundle = bundleOf(KEY_USER to user)
            addToBackStack(null)
            replace<UserFragment>(R.id.fragmentContainer, args = bundle)
        }
    }
}

const val KEY_USER = "keySomeUser"