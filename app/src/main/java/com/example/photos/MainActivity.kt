package com.example.photos

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val photoList = mutableListOf<Photo>()
        findViewById<ImageButton>(R.id.ibtn_add_photo).setOnClickListener {
            val photo = Photo(id, uri)
            photoList.add(photo)
        }

        val adapter = PhotoAdapter(PhotoDiffCallback())
        adapter.submitList(photoList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 4)
    }
}