package com.bachld.intentcallaction

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.net.toUri

class CallActivity : AppCompatActivity() {
    lateinit var inputSDT: EditText
    lateinit var btnCall: ImageButton
    lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_call)
        inputSDT = findViewById(R.id.input_sdt)
        btnCall = findViewById(R.id.btn_call)
        btnBack = findViewById(R.id.btn_back)
        btnCall.setOnClickListener {
            val phoneNumber = inputSDT.text.toString().trim()
            if (phoneNumber.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = "tel:$phoneNumber".toUri()
                }
                startActivity(intent)
            } else {
                inputSDT.error = "Please enter a phone number"
            }
        }
        btnBack.setOnClickListener {
            finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}