package com.example.photos

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val requestCodeImageGet = 100

    private val imagePermissions = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

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

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data
            }
        }

        button.setOnClickListener{
            if (checkPermissions()) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                activityResultLauncher.launch(intent)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            requestCodeImageGet -> {
                if (grantResults.isNotEmpty()) {
                    for ((i) in permissions.withIndex()) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            Log.i("PermissionsResult", "finish")
                            finish()
                        }
                    }
                }
            }
        }
    }

    fun checkPermissions(): Boolean {
        val denyPermissions = ArrayList<String>()

        for (i in imagePermissions) {
            if (ContextCompat.checkSelfPermission(this, i) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(i)
            }
        }

        if (denyPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, denyPermissions.toTypedArray(), requestCodeImageGet)
            return false
        }
        return true
    }
}