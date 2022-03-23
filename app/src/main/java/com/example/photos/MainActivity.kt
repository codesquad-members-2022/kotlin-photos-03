package com.example.photos

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val requestCodeImageGet = 100

    private val imagePermissions = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button_image)

        button.setOnClickListener{
            if (checkPermissions()) {
                loadImage()
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

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val image = findViewById<ImageView>(R.id.imageView)

        if (requestCode == requestCodeImageGet) {
            val uri = data?.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                image.setImageBitmap(bitmap)
            } catch (e: Exception) {
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

    fun loadImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), requestCodeImageGet)
    }
}