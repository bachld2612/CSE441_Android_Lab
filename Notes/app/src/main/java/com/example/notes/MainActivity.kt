package com.example.notes

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var arrayWork: ArrayList<String>
    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var etWork: EditText
    lateinit var etHour: EditText
    lateinit var etMinute: EditText
    lateinit var txtDate: TextView
    lateinit var btnAdd: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.lvWorks)
        etWork = findViewById(R.id.etWork)
        etHour = findViewById(R.id.etHour)
        etMinute = findViewById(R.id.etMinute)
        txtDate = findViewById(R.id.tvToday)
        btnAdd = findViewById(R.id.btnAdd)
        arrayWork = ArrayList()
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayWork)
        listView.adapter = arrayAdapter
        val date = Calendar.getInstance().time
        val simpleDateFormat = java.text.SimpleDateFormat("dd/MM/yyyy")
        txtDate.text = "Hôm nay: " + simpleDateFormat.format(date)
        btnAdd.setOnClickListener{
            if(etWork.text.toString().equals("") || etHour.text.toString().equals("") || etMinute.text.toString().equals("")){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Thông báo")
                builder.setMessage("Vui lòng nhập đầy đủ thông tin công việc")
                builder.setPositiveButton("Continue") { dialog, _ -> dialog.dismiss() }
                builder.show()
            } else {
                val work = "${etWork.text} - ${etHour.text}:${etMinute.text}"
                arrayWork.add(work)
                arrayAdapter.notifyDataSetChanged()
                etWork.text.clear()
                etHour.text.clear()
                etMinute.text.clear()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}