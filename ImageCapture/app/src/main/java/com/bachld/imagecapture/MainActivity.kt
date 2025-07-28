package com.bachld.imagecapture

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.provider.MediaStore.ACTION_IMAGE_CAPTURE
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var cameraPreview: ImageView
    lateinit var btnCapture: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        cameraPreview = findViewById(R.id.camera_preview)
        btnCapture = findViewById(R.id.btn_capture)
        btnCapture.setOnClickListener {
            val intent = Intent(ACTION_IMAGE_CAPTURE)
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    1
                )
                return@setOnClickListener
            }

            startActivityForResult(intent, 99)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 99 && resultCode == Activity.RESULT_OK) {
            val photo = data?.extras?.get("data") as? Bitmap
            photo?.let {
                cameraPreview.setImageBitmap(it)
            }
        }
    }
}