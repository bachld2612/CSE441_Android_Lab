package com.bachld.yearexchange

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var inputYear: EditText
    lateinit var btnConvert: Button
    lateinit var lunarYearResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inputYear = findViewById(R.id.inputYear)
        btnConvert = findViewById(R.id.btnConvert)
        lunarYearResult = findViewById(R.id.lunarYearResult)
        btnConvert.setOnClickListener {
            val year = inputYear.text.toString().toIntOrNull() ?: 0
            val can = when (year % 10) {
                0 -> "Canh"
                1 -> "Tân"
                2 -> "Nhâm"
                3 -> "Quý"
                4 -> "Giáp"
                5 -> "Ất"
                6 -> "Bính"
                7 -> "Đinh"
                8 -> "Mậu"
                9 -> "Kỷ"
                else -> ""
            }
            val chi = when (year % 12) {
                0 -> "Thân"
                1 -> "Dậu"
                2 -> "Tuất"
                3 -> "Hợi"
                4 -> "Tý"
                5 -> "Sửu"
                6 -> "Dần"
                7 -> "Mão"
                8 -> "Thìn"
                9 -> "Tỵ"
                10 -> "Ngọ"
                11 -> "Mùi"
                else -> ""
            }
            val lunarYear = "$can $chi"
            lunarYearResult.text = lunarYear
        }
    }
}