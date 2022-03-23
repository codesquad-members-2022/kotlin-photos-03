package com.example.photos

import androidx.recyclerview.widget.DiffUtil

class PhotoDiffCallback: DiffUtil.ItemCallback<Photo>() {

    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.uri == newItem.uri
    }


}