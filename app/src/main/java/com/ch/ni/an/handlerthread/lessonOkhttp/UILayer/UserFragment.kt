package com.ch.ni.an.handlerthread.lessonOkhttp.UILayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.databinding.FragmentUserDetailBinding
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.UserModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Extensions.toUserUiLayer
import com.ch.ni.an.handlerthread.lessonOkhttp.UILayer.adapters.UserDetailAdapter
import com.ch.ni.an.handlerthread.lessonOkhttp.UILayer.adapters.UsersAdapter
import com.ch.ni.an.handlerthread.lessonOkhttp.UILayer.viewModels.UserViewModel
import com.google.android.material.snackbar.Snackbar

class UserFragment: Fragment() {

    private var _binding: FragmentUserDetailBinding? = null
    private val binding : FragmentUserDetailBinding get() = _binding!!
    private val myModel: UserViewModel by activityViewModels()
    private lateinit var adapter: UserDetailAdapter

    private val recyclerView: RecyclerView by lazy {
        binding.recyclerView
    }



    override fun onCreateView(
        inflater :LayoutInflater,
        container :ViewGroup?,
        savedInstanceState :Bundle?) :View? {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view :View, savedInstanceState :Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = requireArguments().getParcelable<User>(KEY_USER)?.toUserUiLayer()

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserDetailAdapter {
            val post = it.tag as PostModel
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
            val newPostModel: PostModel = PostModel(1, "My First Post", "I add my First Post", user.id )
            myModel.addPost(newPostModel)

        }

        myModel.newPost.observe(this, {
            it?.let {
                Snackbar.make(view, it.toString(), Snackbar.LENGTH_SHORT).show()
            }
        })


        myModel.patchPost.observe(this, {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
        })


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}