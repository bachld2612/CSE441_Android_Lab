package com.bachld.caculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var edta: EditText
    private lateinit var edtb: EditText
    private lateinit var edtkq: EditText
    private lateinit var btntong: Button
    private lateinit var btnclear: Button
    private lateinit var txtlichsu: TextView
    private var lichsu: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edta = findViewById(R.id.edtA)
        edtb = findViewById(R.id.edtB)
        edtkq = findViewById(R.id.tvResult)
        btntong = findViewById(R.id.btnSum)
        btnclear = findViewById(R.id.btnClear)
        txtlichsu = findViewById(R.id.tvHistory)

        val myprefs = getSharedPreferences("mysave", MODE_PRIVATE)
        lichsu = myprefs.getString("ls", "") ?: ""
        txtlichsu.text = lichsu

        // Xử lý nút TỔNG
        btntong.setOnClickListener {
            val a = edta.text.toString().toIntOrNull() ?: 0
            val b = edtb.text.toString().toIntOrNull() ?: 0
            val kq = a + b
            edtkq.setText(kq.toString())
            lichsu += "$a + $b = $kq"
            txtlichsu.text = lichsu
            lichsu += "\n"
        }

        btnclear.setOnClickListener {
            lichsu = ""
            txtlichsu.text = lichsu
        }
    }

    override fun onPause() {
        super.onPause()
        val myprefs = getSharedPreferences("mysave", MODE_PRIVATE)
        val myedit = myprefs.edit()
        myedit.putString("ls", lichsu)
        myedit.apply()
    }
}
