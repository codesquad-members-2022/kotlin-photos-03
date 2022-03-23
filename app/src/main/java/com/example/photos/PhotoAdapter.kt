package com.example.photos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PhotoAdapter(diffCallback: DiffUtil.ItemCallback<Photo>) : ListAdapter<Photo, PhotoAdapter.ViewHolder>(diffCallback){
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private  val item : ImageView = itemView.findViewById<View>(R.id.iv_item) as ImageView
        fun bind(photo:Photo){
            item.layoutParams.width=100
            item.layoutParams.height=100
            item.setImageURI(photo.uri)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}