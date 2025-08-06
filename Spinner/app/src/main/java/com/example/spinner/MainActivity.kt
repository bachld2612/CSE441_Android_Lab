package com.example.spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var arr1: Array<String> = arrayOf(
        "Hàng Điện tử", "Hàng Hóa Chất",
        "Hàng Gia dụng", "Hàng xây dựng"
    )
    lateinit var txt: TextView
    lateinit var spinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        txt = findViewById(R.id.txt1)
        spinner = findViewById(R.id.spinner1)
        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, arr1)
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                txt.text = "Bạn đã chọn: ${parent.getItemAtPosition(position)}"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                txt.text = "Bạn chưa chọn gì cả"
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}