package com.bachld.intentfilter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var inputLink: EditText
    lateinit var btnOpenLink: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnOpenLink = findViewById(R.id.btn_openWeb)
        inputLink = findViewById(R.id.input_link)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnOpenLink.setOnClickListener {
            val intent = Intent()
            intent.setClassName(
                "com.bachld.intentfilters2", // package name của App 2
                "com.bachld.intentfilters2.MainActivity" // full class name của Activity App 2
            )
            intent.data = "https://${inputLink.text}".toUri()
            startActivity(intent)
        }

    }
}