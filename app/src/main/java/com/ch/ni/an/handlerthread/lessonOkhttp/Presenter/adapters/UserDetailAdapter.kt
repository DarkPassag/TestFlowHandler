package com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ch.ni.an.handlerthread.databinding.RecyclerviewPostItemBinding
import com.ch.ni.an.handlerthread.lessonOkhttp.Presenter.models.PostUiModel


class UserDetailAdapter(
    private val onClickListener: View.OnClickListener
) :
    ListAdapter<PostUiModel, UserDetailAdapter.UserDetailHolder>(UserDetailDiffUtil) {


    class UserDetailHolder(
       private val binding : RecyclerviewPostItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item:PostUiModel){
            binding.titleTextView.text = item.title
            binding.bodyTextView.text = item.body
        }

    }

    override fun onCreateViewHolder(parent :ViewGroup, viewType :Int) :UserDetailHolder {
        val binding = RecyclerviewPostItemBinding.inflate(LayoutInflater.from(parent.context))
        return UserDetailHolder(binding)
    }

    override fun onBindViewHolder(holder :UserDetailHolder, position :Int) {
        val item = getItem(position)
        holder.itemView.tag = item
        holder.itemView.setOnClickListener(onClickListener)
        holder.bind(item)
    }

    object UserDetailDiffUtil : DiffUtil.ItemCallback<PostUiModel>() {
        override fun areItemsTheSame(oldItem :PostUiModel, newItem :PostUiModel) :Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem :PostUiModel, newItem :PostUiModel) :Boolean {
            return oldItem == newItem
        }

    }
}