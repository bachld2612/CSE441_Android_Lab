package com.example.sumtwonumber

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var inputA: EditText;
    lateinit var inputB: EditText;
    lateinit var inputKQ: EditText;
    lateinit var btnSum: Button;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inputA = findViewById<EditText>(R.id.input_nhapA);
        inputB = findViewById<EditText>(R.id.input_nhapB);
        inputKQ = findViewById<EditText>(R.id.input_ketQua);
        btnSum = findViewById<Button>(R.id.btn_tong);
        btnSum.setOnClickListener {
            val a = inputA.text.toString().toIntOrNull()?:0;
            val b = inputB.text.toString().toIntOrNull()?:0;
            val  c = a + b;
            inputKQ.setText(getString(R.string.result_text, c))
        }
    }
}