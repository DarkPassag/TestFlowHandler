package com.ch.ni.an.handlerthread.lessonOkhttp.UILayer.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.databinding.RecyclerviewItemBinding
import com.ch.ni.an.handlerthread.databinding.RecyclerviewUserBinding
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.User

class UsersAdapter(
    private val onClick: View.OnClickListener
): ListAdapter<User, UsersAdapter.UserHolder>(UserDiffUtil) {


    class UserHolder(
        private val binding: RecyclerviewUserBinding
    ): RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(user: User){
            with(binding){
                nameTextView.text = user.name
                surNameTextView.text = user.username
                phoneTextView.text = user.phone
                addressTextView.text = user.address.city
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



    object UserDiffUtil: DiffUtil.ItemCallback<User>() {


        override fun areItemsTheSame(oldItem :User, newItem :User) :Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem :User, newItem :User) :Boolean {
            return  oldItem == newItem
        }
    }
}