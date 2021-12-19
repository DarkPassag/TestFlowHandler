package com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.databinding.RecyclerviewUserBinding
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.models.UserUiModel

class UsersAdapter(
    private val onClick: View.OnClickListener
): ListAdapter<UserUiModel, UsersAdapter.UserHolder>(UserDiffUtil) {


    class UserHolder(
        private val binding: RecyclerviewUserBinding
    ): RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(user: UserUiModel){
            with(binding){
                nameTextView.text = user.name
                phoneTextView.text = user.phone
                addressTextView.text = user.address
            }
        }
    }

    override fun onCreateViewHolder(parent :ViewGroup, viewType :Int) :UserHolder {
        val binding = RecyclerviewUserBinding.inflate(LayoutInflater.from(parent.context))
        return  UserHolder(binding)
    }

    override fun onBindViewHolder(holder :UserHolder, position :Int) {
        val item = getItem(position)
        holder.itemView.tag = item
        holder.bind(item)
        holder.itemView.setOnClickListener(onClick)
    }



    object UserDiffUtil: DiffUtil.ItemCallback<UserUiModel>() {


        override fun areItemsTheSame(oldItem :UserUiModel, newItem :UserUiModel) :Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem :UserUiModel, newItem :UserUiModel) :Boolean {
            return  oldItem == newItem
        }
    }
}