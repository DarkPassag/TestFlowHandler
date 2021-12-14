package com.ch.ni.an.handlerthread.presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.databinding.HeaderLayoutBinding
import com.ch.ni.an.handlerthread.model.HeadClassModel

class HeaderAdapter(
    private val clicker: (View) -> Unit
): ListAdapter<HeadClassModel, HeaderAdapter.HeaderHolder>(HeaderDiffCallback) {


    class HeaderHolder(
        private val binding :HeaderLayoutBinding
    ): RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(item: HeadClassModel){
            binding.headerTextView.text = item.head
        }
    }

    override fun onCreateViewHolder(parent :ViewGroup, viewType :Int) :HeaderHolder {
        val binding = HeaderLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return HeaderHolder(binding)
    }

    override fun onBindViewHolder(holder :HeaderHolder, position :Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener(clicker)
        holder.itemView.tag = item
        holder.bind(item)
    }



    object HeaderDiffCallback: DiffUtil.ItemCallback<HeadClassModel>(){
        override fun areItemsTheSame(oldItem :HeadClassModel, newItem :HeadClassModel) :Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem :HeadClassModel, newItem :HeadClassModel) :Boolean {
            return oldItem == newItem
        }

    }

}


