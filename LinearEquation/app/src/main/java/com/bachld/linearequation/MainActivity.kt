package com.bachld.linearequation

import android.content.Intent
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
    lateinit var btnKQ: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inputA = findViewById(R.id.input_a)
        inputB = findViewById(R.id.input_b)
        btnKQ = findViewById(R.id.btn_ket_qua)
        btnKQ.setOnClickListener {
            val myIntent = Intent(this, ResultActivity::class.java)
            val bundle = Bundle()
            val a = inputA.text.toString().toIntOrNull()?: 0
            val b = inputB.text.toString().toIntOrNull()?: 0
            bundle.putInt("a", a)
            bundle.putInt("b", b)
            myIntent.putExtra("myBundle", bundle)
            startActivity(myIntent)
        }
    }
}