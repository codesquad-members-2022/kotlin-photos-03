package com.example.photos
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DoodleAdapter(diffCallback: DiffUtil.ItemCallback<JsonImage>) : ListAdapter<JsonImage, DoodleAdapter.ViewHolder>(diffCallback){
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private  val item : ImageView = itemView.findViewById<View>(R.id.iv_doodle_item) as ImageView
        fun bind(jsonImage: JsonImage){
            item.setImageURI(jsonImage.uri)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_doodle_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}