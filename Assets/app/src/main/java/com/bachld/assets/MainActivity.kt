package com.bachld.assets

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory


class MainActivity : AppCompatActivity() {

    var btnparse: Button? = null
    var lv: ListView? = null
    var mylist: ArrayList<String?>? = null
    var myadapter: ArrayAdapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnparse = findViewById(R.id.btnparse)
        lv = findViewById(R.id.lv1)
        mylist = ArrayList()
        myadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mylist!!)
        lv!!.adapter = myadapter
        btnparse?.setOnClickListener {
            try {
                val inputStream = assets.open("employee.xml")
                val fc: XmlPullParserFactory = XmlPullParserFactory.newInstance()
                val parser: XmlPullParser = fc.newPullParser()
                parser.setInput(inputStream, null)

                var eventType = parser.eventType
                var nodeName: String?
                var datashow = ""

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    when (eventType) {
                        XmlPullParser.START_TAG -> {
                            nodeName = parser.name
                            if (nodeName == "employee") {
                                datashow += parser.getAttributeValue(0) + "-"
                                datashow += parser.getAttributeValue(1) + "-"
                            } else if (nodeName == "name") {
                                datashow += parser.nextText() + "-"
                            } else if (nodeName == "phone") {
                                datashow += parser.nextText()
                            }
                        }
                        XmlPullParser.END_TAG -> {
                            if (parser.name == "employee") {
                                mylist!!.add(datashow)
                                datashow = ""
                            }
                        }
                    }
                    eventType = parser.next()
                }

                myadapter?.notifyDataSetChanged()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }
}