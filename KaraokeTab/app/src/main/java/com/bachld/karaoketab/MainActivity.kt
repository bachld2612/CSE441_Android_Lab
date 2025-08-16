package com.bachld.karaoketab

import android.app.Activity
import android.os.Bundle
import android.widget.EditText
import android.widget.ListView
import android.widget.TabHost

class MainActivity : Activity() {
    private lateinit var edttim: EditText
    private lateinit var lv1: ListView
    private lateinit var lv2: ListView
    private lateinit var lv3: ListView
    private lateinit var tab: TabHost

    private lateinit var list1: ArrayList<Item>
    private lateinit var list2: ArrayList<Item>
    private lateinit var list3: ArrayList<Item>

    private lateinit var myarray1: MyArrayAdapter
    private lateinit var myarray2: MyArrayAdapter
    private lateinit var myarray3: MyArrayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addControl()
        addEvent()
    }

    private fun addEvent() {
        tab.setOnTabChangedListener { tabId ->
            when (tabId.lowercase()) {
                "t1" -> {
                    list1.clear()
                    list1.add(Item("52300", "Em là ai Tôi là ai", 0))
                    list1.add(Item("52600", "Chén Đắng", 1))
                    myarray1.notifyDataSetChanged()
                }
                "t2" -> {
                    list2.clear()
                    list2.add(Item("57236", "Gởi em ở cuối sông hồng", 0))
                    list2.add(Item("51548", "Say tình", 0))
                    myarray2.notifyDataSetChanged()
                }
                "t3" -> {
                    list3.clear()
                    list3.add(Item("57689", "Hát với dòng sông", 1))
                    list3.add(Item("58716", "Say tình _ Remix", 0))
                    myarray3.notifyDataSetChanged()
                }
            }
        }
    }

    private fun addControl() {
        tab = findViewById(R.id.tabhost)
        tab.setup()

        val tab1 = tab.newTabSpec("t1")
        tab1.setContent(R.id.tab1)
        tab1.setIndicator("", resources.getDrawable(R.drawable.like, theme))
        tab.addTab(tab1)

        val tab2 = tab.newTabSpec("t2")
        tab2.setContent(R.id.tab2)
        tab2.setIndicator("", resources.getDrawable(R.drawable.like, theme))
        tab.addTab(tab2)

        val tab3 = tab.newTabSpec("t3")
        tab3.setContent(R.id.tab3)
        tab3.setIndicator("", resources.getDrawable(R.drawable.like, theme))
        tab.addTab(tab3)

        edttim = findViewById(R.id.edttim)
        lv1 = findViewById(R.id.lv1)
        lv2 = findViewById(R.id.lv2)
        lv3 = findViewById(R.id.lv3)

        list1 = ArrayList()
        list2 = ArrayList()
        list3 = ArrayList()

        myarray1 = MyArrayAdapter(this, R.layout.listitem, list1)
        myarray2 = MyArrayAdapter(this, R.layout.listitem, list2)
        myarray3 = MyArrayAdapter(this, R.layout.listitem, list3)

        lv1.adapter = myarray1
        lv2.adapter = myarray2
        lv3.adapter = myarray3
    }
}
