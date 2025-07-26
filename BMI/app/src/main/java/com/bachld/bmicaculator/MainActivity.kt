package com.bachld.bmicaculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var inputName: EditText
    lateinit var inputHeight: EditText
    lateinit var inputWeight: EditText
    lateinit var btnCalculate: Button
    lateinit var resultBMI: TextView
    lateinit var diagnosis: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inputName = findViewById(R.id.inputName)
        inputHeight = findViewById(R.id.inputHeight)
        inputWeight = findViewById(R.id.inputWeight)
        btnCalculate = findViewById(R.id.btnCalculate)
        resultBMI = findViewById(R.id.resultBMI)
        diagnosis = findViewById(R.id.diagnosis)
        btnCalculate.setOnClickListener {
            val height = inputHeight.text.toString().toFloatOrNull() ?: 0f
            val weight = inputWeight.text.toString().toFloatOrNull() ?: 0f

            val bmi: Float = if (height > 0f) {
                weight / ((height / 100f) * (height / 100f))
            } else {
                0f
            }
            resultBMI.text = String.format("%.2f", bmi)
            when{
                bmi < 18.5 -> {
                    diagnosis.text = "Bạn gầy"
                }
                bmi in 18.5..24.9 -> {
                    diagnosis.text = "Bạn bình thường"
                }
                bmi in 25.0..29.9 -> {
                    diagnosis.text = "Bạn béo phì độ 1"
                }
                bmi in 30.0..34.9 -> {
                    diagnosis.text = "Bạn béo phì độ 2"
                }
                else -> {
                    diagnosis.text = "Bạn béo phì độ 3"
                }
            }
        }
    }
}