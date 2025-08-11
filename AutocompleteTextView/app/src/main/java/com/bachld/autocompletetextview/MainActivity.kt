package com.bachld.autocompletetextview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {

    private lateinit var selection: TextView
    private lateinit var singleComplete: AutoCompleteTextView
    private lateinit var multiComplete: MultiAutoCompleteTextView

    private val arr = arrayOf(
        "hà nội", "huế", "Sài gòn",
        "hà giang", "Hội an", "Kiên giang",
        "Lâm đồng", "Long khánh"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        selection = findViewById(R.id.selection)
        singleComplete = findViewById(R.id.editauto)
        multiComplete = findViewById(R.id.multiAutoCompleteTextView1)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)
        singleComplete.setAdapter(adapter)
        multiComplete.setAdapter(adapter)
        multiComplete.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        singleComplete.doOnTextChanged {
            text, _,_ ,_ -> selection.text = text ?: ""
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}