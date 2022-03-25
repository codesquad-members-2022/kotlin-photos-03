package com.example.photos

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val requestCodeImageGet = 100

    private val imagePermissions = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<ImageButton>(R.id.button)

//        activityResultLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if (it.resultCode == Activity.RESULT_OK) {
//                val uri = it.data?.data
//            }
//        }
//
//        button.setOnClickListener{
//            if (checkPermissions()) {
//                val intent = Intent(Intent.ACTION_GET_CONTENT)
//                intent.type = "image/*"
//                activityResultLauncher.launch(intent)
//            }
//        }

        button.setOnClickListener {
            val intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
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
            ActivityCompat.requestPermissions(
                this,
                denyPermissions.toTypedArray(),
                requestCodeImageGet
            )
            return false
        }
        return true
    }
}