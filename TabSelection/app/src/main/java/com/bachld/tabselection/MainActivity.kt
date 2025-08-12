package com.bachld.tabselection

import android.app.Activity
import android.os.Bundle
import android.widget.*
import androidx.core.content.ContextCompat

class MainActivity : Activity() {

    private lateinit var edta: EditText
    private lateinit var edtb: EditText
    private lateinit var btncong: Button
    private lateinit var lv1: ListView

    private val list = ArrayList<String>()
    private lateinit var myarray: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControl()
        addEvent()
    }

    private fun addControl() {
        val tab: TabHost = findViewById(android.R.id.tabhost)
        tab.setup()

        val iconAdd = ContextCompat.getDrawable(this, R.drawable.cong)
        val iconHistory = ContextCompat.getDrawable(this, R.drawable.lichsu)

        val tab1 = tab.newTabSpec("t1").apply {
            setContent(R.id.tab1)
            setIndicator("", iconAdd)
        }
        tab.addTab(tab1)

        val tab2 = tab.newTabSpec("t2").apply {
            setContent(R.id.tab2)
            setIndicator("", iconHistory)
        }
        tab.addTab(tab2)
        tab.currentTab = 0

        edta = findViewById(R.id.edtA)
        edtb = findViewById(R.id.edtB)
        btncong = findViewById(R.id.btnAdd)
        lv1 = findViewById(R.id.lvHistory)

        myarray = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        lv1.adapter = myarray
    }

    private fun addEvent() {
        btncong.setOnClickListener { xulyCong() }
    }

    private fun xulyCong() {
        val a = edta.text.toString().toIntOrNull()
        val b = edtb.text.toString().toIntOrNull()

        if (a != null && b != null) {
            val c = "$a + $b = ${a + b}"
            list.add(c)
            myarray.notifyDataSetChanged()
            edta.text.clear()
            edtb.text.clear()
        } else {
            Toast.makeText(this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show()
        }
    }
}