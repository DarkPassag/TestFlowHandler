package com.ch.ni.an.handlerthread.lessonOkhttp.Presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.databinding.FragmentUserDetailBinding
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.adapters.UserDetailAdapter
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.models.PostUiModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.models.UserUiModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels.UserDetailFactoryViewModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels.UserViewModel
import com.google.android.material.snackbar.Snackbar

class UserFragment: Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding : FragmentUserDetailBinding get() = _binding!!
    private lateinit var myModel: UserViewModel
    private lateinit var factoryModel: UserDetailFactoryViewModel
    private lateinit var adapter: UserDetailAdapter

    private val recyclerView: RecyclerView by lazy {
        binding.recyclerView
    }



    override fun onCreateView(
        inflater :LayoutInflater,
        container :ViewGroup?,
        savedInstanceState :Bundle?) :View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        factoryModel = UserDetailFactoryViewModel()
        myModel = ViewModelProvider(this, factoryModel).get(UserViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view :View, savedInstanceState :Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = requireArguments().getParcelable<UserUiModel>(KEY_USER)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserDetailAdapter {
            val post = it.tag as PostUiModel
            myModel.patchPost(post.id, "This is new title by patch")
        }
        recyclerView.adapter = adapter

        myModel.post.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        with(binding){
            checkNotNull(user)
            myModel.getPosts(user.id)
            userNameTextView.text = user.name
            userAddressTextView.text = user.address
            userPhoneTextView.text = user.phone
            userEmailTextView.text = user.email
            userCompanyTextView.text = user.company
            userWebsiteTextView.text = user.website
        }

        binding.fab.setOnClickListener {
            checkNotNull(user)
            val newPostModel: PostUiModel = PostUiModel(1, "My First Post", "I add my First Post", user.id )
            myModel.addPost(newPostModel)

        }

        myModel.newPost.observe(this, {
            it?.let {
                Snackbar.make(view, it.toString(), Snackbar.LENGTH_SHORT).show()
            }
        })


        myModel.patchPosts.observe(this, {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
        })


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}