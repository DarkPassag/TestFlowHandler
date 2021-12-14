package com.ch.ni.an.handlerthread.presenter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.databinding.RecyclerviewItemBinding
import com.ch.ni.an.handlerthread.model.Post

class PostAdapter(

): ListAdapter<Post, PostAdapter.PostHolder>(ItemDiffUtil()) {


    class PostHolder(
        private val binding :RecyclerviewItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post){
            binding.completeTextView.text = item.completed.toString()
            binding.titleTextView.text = item.title.toString()

        }
    }

    override fun onCreateViewHolder(parent :ViewGroup, viewType :Int) :PostHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context))
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder :PostHolder, position :Int) {
        holder.bind(getItem(position))
    }

    class ItemDiffUtil: DiffUtil.ItemCallback<Post>(){

        override fun areItemsTheSame(oldItem :Post, newItem :Post) :Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem :Post, newItem :Post) :Boolean {
            return oldItem == newItem
        }

    }
}