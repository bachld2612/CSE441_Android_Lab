package com.bachld.customlayout

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var lv: ListView

    private val namephone = arrayOf(
        "Điện thoại iPhone 12",
        "Điện thoại SamSung S20",
        "Điện thoại Nokia 6",
        "Điện thoại Bphone 2020",
        "Điện thoại Oppo 5",
        "Điện thoại VSmart Joy2"
    )

    private val imagephone = intArrayOf(
        R.drawable.ip,
        R.drawable.samsung,
        R.drawable.htc,
        R.drawable.lg,
        R.drawable.wp,
        R.drawable.sky
    )

    private val mylist = arrayListOf<Phone>()
    private lateinit var myadapter: MyArrayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lv = findViewById(R.id.lv)

        // build data
        for (i in namephone.indices) {
            mylist.add(Phone(namephone[i], imagephone[i]))
        }

        myadapter = MyArrayAdapter(this, R.layout.layout_listview, mylist)
        lv.adapter = myadapter

        lv.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("name", namephone[position])
            startActivity(intent)
        }
    }
}
