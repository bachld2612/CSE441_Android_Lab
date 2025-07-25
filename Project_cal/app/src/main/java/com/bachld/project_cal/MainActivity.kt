package com.bachld.project_cal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var inputA: EditText
    lateinit var inputB: EditText
    lateinit var inputKQ: EditText
    lateinit var btnTong: Button
    lateinit var btnHieu: Button
    lateinit var btnTich: Button
    lateinit var btnThuong: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inputA = findViewById(R.id.input_nhapA)
        inputB = findViewById(R.id.input_nhapB)
        inputKQ = findViewById(R.id.input_kq)
        btnTong = findViewById(R.id.btn_tong)
        btnHieu = findViewById(R.id.btn_hieu)
        btnTich = findViewById(R.id.btn_tich)
        btnThuong = findViewById(R.id.btn_thuong)
        btnTong.setOnClickListener {
            val a = inputA.text.toString().toIntOrNull() ?: 0
            val b = inputB.text.toString().toIntOrNull() ?: 0
            inputKQ.setText("a + b = " + (a + b).toString())
        }
        btnHieu.setOnClickListener {
            val a = inputA.text.toString().toIntOrNull() ?: 0
            val b = inputB.text.toString().toIntOrNull() ?: 0
            inputKQ.setText("a - b = " + (a - b).toString())
        }
        btnTich.setOnClickListener {
            val a = inputA.text.toString().toIntOrNull() ?: 0
            val b = inputB.text.toString().toIntOrNull() ?: 0
            inputKQ.setText("a * b = " + (a * b).toString())
        }
        btnThuong.setOnClickListener {
            val a = inputA.text.toString().toIntOrNull() ?: 0
            val b = inputB.text.toString().toIntOrNull() ?: 1
            if (b == 0) {
                inputKQ.setText("B phai khac 0")
            } else {
                inputKQ.setText("a / b = " + (a / b).toString())
            }
        }
    }
}