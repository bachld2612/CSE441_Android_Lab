package com.bachld.intentservice

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btnStop: ImageButton
    lateinit var btnPlay: ImageButton
    var flag: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnPlay = findViewById(R.id.btn_play)
        btnStop = findViewById(R.id.btn_stop)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnPlay.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
            if(flag){
                btnPlay.setImageResource(R.drawable.stop)
                flag = false
            }else{
                btnPlay.setImageResource(R.drawable.play)
                flag = true
            }
        }

        btnStop.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
            btnPlay.setImageResource(R.drawable.play)
            flag = true
        }
    }
}