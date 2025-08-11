package com.bachld.customgridview

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SubActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.childlayout)

        val txtname2 = findViewById<TextView>(R.id.textView2)
        val img2 = findViewById<ImageView>(R.id.imageView2)

        val extras = intent.extras
        val position = extras?.getInt("TITLE") ?: 0

        txtname2.text = MainActivity.arrayName[position]
        img2.setImageResource(MainActivity.imageName[position])
    }
}