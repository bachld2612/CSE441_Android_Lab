package com.bachld.demorcyclefile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var btnDialog: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnDialog = findViewById<Button>(R.id.btn_dialog);
        btnDialog.setOnClickListener {
            val intent = Intent(this, Subactivity::class.java);
            startActivity(intent);
        }
    }
}