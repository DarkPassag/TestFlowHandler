package com.ch.ni.an.handlerthread.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.R
import com.ch.ni.an.handlerthread.databinding.RecyclerviewItemBinding
import com.ch.ni.an.handlerthread.model.Post

class PostAdapter(

): ListAdapter<Post, PostAdapter.PostHolder>(ItemDiffUtil()) {
    class PostHolder(
        private val binding :RecyclerviewItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent :ViewGroup, viewType :Int) :PostHolder {
        val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context))


        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder :PostHolder, position :Int) {
        val item = getItem(position)
        holder.itemView.apply {
            findViewById<TextView>(R.id.titleTextView).text = item.title
            findViewById<TextView>(R.id.completeTextView).text = item.completed.toString()
        }

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