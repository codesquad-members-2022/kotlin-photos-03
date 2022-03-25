package com.example.photos

import androidx.recyclerview.widget.DiffUtil

class JSonDiffCallback: DiffUtil.ItemCallback<JsonImage>() {
    override fun areItemsTheSame(oldItem: JsonImage, newItem: JsonImage): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: JsonImage, newItem: JsonImage): Boolean {
        return oldItem==newItem
    }


}