package com.ch.ni.an.handlerthread.lessonOkhttp.UILayer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.databinding.RecyclerviewPostItemBinding
import com.ch.ni.an.handlerthread.lessonOkhttp.DataLayer.PostModel


class UserDetailAdapter :
    ListAdapter<PostModel, UserDetailAdapter.UserDetailHolder>(UserDetailDiffUtil) {


    class UserDetailHolder(
       private val binding : RecyclerviewPostItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item:PostModel){
            binding.titleTextView.text = item.title
            binding.bodyTextView.text = item.body
        }

    }

    override fun onCreateViewHolder(parent :ViewGroup, viewType :Int) :UserDetailHolder {
        val binding = RecyclerviewPostItemBinding.inflate(LayoutInflater.from(parent.context))
        return UserDetailHolder(binding)
    }

    override fun onBindViewHolder(holder :UserDetailHolder, position :Int) {
        holder.bind(getItem(position))
    }

    object UserDetailDiffUtil : DiffUtil.ItemCallback<PostModel>() {
        override fun areItemsTheSame(oldItem :PostModel, newItem :PostModel) :Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem :PostModel, newItem :PostModel) :Boolean {
            return oldItem == newItem
        }

    }
}