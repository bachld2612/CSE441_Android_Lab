package com.bachld.jsonreader

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var btnParse: Button
    private lateinit var lv: ListView
    private lateinit var myList: ArrayList<String>
    private lateinit var myAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnParse = findViewById(R.id.btnparse)
        lv = findViewById(R.id.lv)

        myList = ArrayList()
        myAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myList)
        lv.adapter = myAdapter

        btnParse.setOnClickListener {
            parseJson()
        }
    }

    private fun parseJson() {
        var json: String?
        try {
            // Đọc file JSON từ assets
            val inputStream = assets.open("computer.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charset.forName("UTF-8"))

            // Parse JSON
            val reader = JSONObject(json)
            myList.add(reader.getString("MaDM"))
            myList.add(reader.getString("TenDM"))

            val myArray: JSONArray = reader.getJSONArray("Sanphams")
            for (i in 0 until myArray.length()) {
                val myObj = myArray.getJSONObject(i)
                myList.add("${myObj.getString("MaSP")} - ${myObj.getString("TenSP")}")
                myList.add("${myObj.getInt("SoLuong")} * ${myObj.getInt("DonGia")} = ${myObj.getInt("ThanhTien")}")
                myList.add(myObj.getString("Hinh"))
            }

            myAdapter.notifyDataSetChanged()

        } catch (e1: IOException) {
            e1.printStackTrace()
        } catch (e2: JSONException) {
            e2.printStackTrace()
        }
    }
}