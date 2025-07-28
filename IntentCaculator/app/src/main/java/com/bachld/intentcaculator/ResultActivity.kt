package com.bachld.intentcaculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    lateinit var inputA: EditText
    lateinit var inputB: EditText
    lateinit var btnTong: Button
    lateinit var btnHieu: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        inputA = findViewById(R.id.input_a)
        inputB = findViewById(R.id.input_b)
        btnTong = findViewById(R.id.btn_tong)
        btnHieu = findViewById(R.id.btn_hieu)
        inputA.setText(intent.getIntExtra("input_a", 0).toString())
        inputB.setText(intent.getIntExtra("input_b", 0).toString())
        btnTong.setOnClickListener {
            val a = intent.getIntExtra("input_a", 0)
            val b = intent.getIntExtra("input_b", 0)
            val result = a + b
            intent.putExtra("result", result)
            setResult(RESULT_OK, intent)
            finish()
        }
        btnHieu.setOnClickListener {
            val a = intent.getIntExtra("input_a", 0)
            val b = intent.getIntExtra("input_b", 0)
            val result = a - b
            intent.putExtra("result", result)
            setResult(RESULT_OK, intent)
            finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}