package com.ch.ni.an.handlerthread.lessonOkhttp.Presenter


import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import com.ch.ni.an.handlerthread.R
import com.ch.ni.an.handlerthread.databinding.FragmentUsersBinding
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.adapters.UsersAdapter
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.models.UserUiModel
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.viewModels.ListUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListUsersFragment : Fragment(R.layout.fragment_users) {

    private var _binding: FragmentUsersBinding? = null

    private val binding get() = _binding!!

    private val myModel by viewModel<ListUserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUsersBinding.bind(view)

        val recyclerView = binding.recyclerView

        val adapter = UsersAdapter {
            val user: UserUiModel = it.tag as UserUiModel
            showUserDetail(user)
        }

        recyclerView.adapter = adapter

        myModel.users.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showUserDetail(user: UserUiModel) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            val bundle = bundleOf(KEY_USER to user)
            addToBackStack(null)
            replace<UserFragment>(R.id.fragmentContainer, args = bundle)
        }
    }
}

const val KEY_USER = "keySomeUser"