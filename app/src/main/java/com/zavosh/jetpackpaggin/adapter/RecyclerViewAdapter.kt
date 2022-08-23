package com.zavosh.jetpackpaggin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zavosh.jetpackpaggin.R
import com.zavosh.jetpackpaggin.data.Result
import kotlinx.android.synthetic.main.recycvler_view_item.view.*

class RecyclerViewAdapter : PagingDataAdapter<Result, RecyclerViewAdapter.ViewHolder>(
    DiffUtilCallBack()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.recycvler_view_item, parent, false)
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: Result) {

            itemView.apply {

                tvName.text = data.name
                tvDesc.text = data.species

                Glide.with(ivLogo)
                    .load(data.image)
                    .centerCrop()
                    .into(ivLogo)

            }

        }

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.name == newItem.name
                    && return oldItem.species == newItem.species
        }

    }

}
