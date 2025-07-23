package com.bachld.demorcyclefile

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Subactivity : AppCompatActivity() {
    lateinit var btnOk: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_subactivity)
        btnOk = findViewById<Button>(R.id.btn_ok)
        btnOk.setOnClickListener { finish(); }
    }
}