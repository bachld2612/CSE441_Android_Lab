package com.bachld.customlayout

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SubActivity : AppCompatActivity() {

    private lateinit var txtSubphone: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        txtSubphone = findViewById(R.id.txt_subphone)

        val namephone = intent.getStringExtra("name")
        txtSubphone.text = namephone
    }
}