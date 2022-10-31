package com.sharmadhiraj.androidpaginglibrarystepbystepimplementationguide.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class FilesListAdapter
    : PagingDataAdapter<String, RecyclerView.ViewHolder>(NewsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            (holder as FilesViewHolder).bind(getItem(position))
    }

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}