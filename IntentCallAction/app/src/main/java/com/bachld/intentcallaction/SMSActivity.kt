package com.bachld.intentcallaction

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SMSActivity : AppCompatActivity() {
    lateinit var btnSMS: ImageButton
    lateinit var btnBack: Button
    lateinit var inputMsg: EditText
    lateinit var input_sdt: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_smsactivity)
        btnSMS = findViewById(R.id.btn_sms)
        btnBack = findViewById(R.id.btn_back)
        inputMsg = findViewById(R.id.input_msg)
        input_sdt = findViewById(R.id.input_sdt)
        btnBack.setOnClickListener {
            finish()
        }
        btnSMS.setOnClickListener {
            val message = inputMsg.text.toString().trim()
            val phoneNumber = input_sdt.text.toString().trim()
            if (message.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    data = "smsto:$phoneNumber".toUri()
                }
                startActivity(Intent.createChooser(intent, "Send SMS"))
            } else {
                inputMsg.error = "Please enter a message"
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}