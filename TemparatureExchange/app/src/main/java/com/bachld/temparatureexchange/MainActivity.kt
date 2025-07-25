package com.bachld.temparatureexchange

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var inputF: EditText
    lateinit var inputC: EditText
    lateinit var btnCvToC: Button
    lateinit var btnCvToF: Button
    lateinit var btnClear: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inputF = findViewById(R.id.input_fd)
        inputC = findViewById(R.id.input_cd)
        btnCvToC = findViewById(R.id.btn_cvC)
        btnCvToF = findViewById(R.id.btn_cvF)
        btnClear = findViewById(R.id.btn_clear)
        btnClear.setOnClickListener {
            inputF.text.clear()
            inputC.text.clear()
        }
        btnCvToC.setOnClickListener {
            val f = inputF.text.toString().toDoubleOrNull()?:0.0
            val c = (f - 32) * 5 / 9
            inputC.setText(DecimalFormat("#.00").format(c))
        }
        btnCvToF.setOnClickListener {
            val c = inputC.text.toString().toDoubleOrNull()?:0.0
            val f = (c * 9 / 5) + 32
            inputF.setText(DecimalFormat("#.00").format(f))
        }
    }
}