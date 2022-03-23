package com.example.photos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val randomColorList=  List<Int>(40){ColorFactory.makeRandomRGB().getColor()}
        val adapter= PhotoAdapter(randomColorList)
        recyclerView.adapter= adapter
        recyclerView.layoutManager= GridLayoutManager(this, 4)
    }
}