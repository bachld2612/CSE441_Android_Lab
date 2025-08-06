package com.example.phonewithlistview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val arr1: Array<String> = arrayOf(
        "Iphone 7", "SamSung Galaxy S7",
        "Nokia Lumia 730", "Sony Xperia XZ", "HTC One E9"
    )
    lateinit var txt: TextView
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        txt = findViewById(R.id.selection)
        listView = findViewById(R.id.lv1)
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, arr1
        )
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            txt.text = "Vị trí $position: ${arr1[position]}"
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}