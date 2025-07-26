package com.bachld.quadraticequation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var inputA: EditText
    lateinit var inputB: EditText
    lateinit var inputC: EditText
    lateinit var btnReset: Button
    lateinit var btnSolve: Button
    lateinit var btnExit: Button
    lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inputA = findViewById(R.id.inputA)
        inputB = findViewById(R.id.inputB)
        inputC = findViewById(R.id.inputC)
        btnReset = findViewById(R.id.btnReset)
        btnSolve = findViewById(R.id.btnSolve)
        btnExit = findViewById(R.id.btnExit)
        txtResult = findViewById(R.id.txtResult)
        btnReset.setOnClickListener {
            inputA.text.clear()
            inputB.text.clear()
            inputC.text.clear()
            txtResult.text = ""
            inputA.requestFocus()
        }
        btnExit.setOnClickListener {
            finish()
        }
        btnSolve.setOnClickListener {
            val a = inputA.text.toString().toIntOrNull() ?: 0
            val b = inputB.text.toString().toIntOrNull() ?: 0
            val c = inputC.text.toString().toIntOrNull() ?: 0
            var result: String
            if(a == 0){
                if (b == 0) {
                    result = if (c == 0) {
                        "Phương trình vô số nghiệm"
                    } else {
                        "Phương trình vô nghiệm"
                    }
                }else
                {
                    result="Pt có 1 No, x="+(-c.toFloat() / b.toFloat());
                }
            }else {
                val delta = b*b - 4 * a * c
                if(delta<0) {
                    result = "Phương trình vô nghiệm"
                }else if (delta == 0) {
                    val x = -b.toFloat() / (2 * a)
                    result = "Phương trình có nghiệm kép, x = $x"
                } else {
                    val x1 = (-b + Math.sqrt(delta.toDouble())) / (2 * a)
                    val x2 = (-b - Math.sqrt(delta.toDouble())) / (2 * a)
                    result = "Phương trình có 2 nghiệm phân biệt, x1 = $x1, x2 = $x2"
                }
            }
            txtResult.text = result
        }
    }
}