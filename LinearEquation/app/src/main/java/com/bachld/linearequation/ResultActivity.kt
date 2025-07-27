package com.bachld.linearequation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    lateinit var btnBackToMain: Button
    lateinit var inputKQ: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        btnBackToMain = findViewById(R.id.btn_back)
        inputKQ = findViewById(R.id.txt_ket_qua)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bundle = intent.getBundleExtra("myBundle")
        val a = bundle?.getInt("a") ?: 0
        val b = bundle?.getInt("b") ?: 0
        if (a == 0 && b == 0) {
            inputKQ.text = "Phương trình vô số nghiệm"
        } else if (a == 0) {
            inputKQ.text = "Phương trình vô nghiệm"
        } else {
            val kq = -b / a.toDouble()
            inputKQ.text = "Nghiệm của phương trình là: $kq"
        }
        btnBackToMain.setOnClickListener {
            startActivity(intent)
        }
    }
}