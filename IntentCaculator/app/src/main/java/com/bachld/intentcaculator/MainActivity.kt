package com.bachld.intentcaculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var inputA: EditText
    lateinit var inputB: EditText
    lateinit var btnKQ: Button
    lateinit var txtKQ: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inputA = findViewById(R.id.input_a)
        inputB = findViewById(R.id.input_b)
        btnKQ = findViewById(R.id.btn_ket_qua)
        txtKQ = findViewById(R.id.txt_kq)
        val resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val kq = data?.getIntExtra("result", 0)
                if (kq != null) {
                    txtKQ.text = "Kết quả: $kq"
                } else {
                    txtKQ.text = "Không có kết quả"
                }
            }
        }
        btnKQ.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("input_a", inputA.text.toString().toIntOrNull()?: 0)
            intent.putExtra("input_b", inputB.text.toString().toIntOrNull()?: 0)
            resultLauncher.launch(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}