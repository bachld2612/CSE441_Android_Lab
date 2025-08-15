package com.bachld.moneyexchange

import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var lvTigia: ListView
    private lateinit var txtDate: TextView
    private lateinit var dsTygia: ArrayList<Tygia>
    private lateinit var myAdapter: MyArrayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvTigia = findViewById(R.id.listView1)
        txtDate = findViewById(R.id.txtDate)

        getDate()

        dsTygia = ArrayList()
        myAdapter = MyArrayAdapter(this, R.layout.layout_listview, dsTygia)
        lvTigia.adapter = myAdapter

        TyGiaTask().execute()
    }

    private fun getDate() {
        val currentDate: Date = Calendar.getInstance().time
        val simpleDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        txtDate.text = "Hôm Nay: ${simpleDate.format(currentDate)}"
    }

    inner class TyGiaTask : AsyncTask<Void, Void, ArrayList<Tygia>>() {

        override fun onPreExecute() {
            super.onPreExecute()
            myAdapter.clear()
        }

        override fun doInBackground(vararg params: Void?): ArrayList<Tygia> {
            val ds = ArrayList<Tygia>()
            try {
                // API mới
                val url = URL("https://open.er-api.com/v6/latest/USD")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.setRequestProperty("User-Agent", "Mozilla/5.0")
                connection.setRequestProperty("Accept", "*/*")

                val stream = connection.inputStream
                val result = stream.bufferedReader().use { it.readText() }

                Log.d("JSON_API", result)

                val jsonObject = JSONObject(result)
                val rates = jsonObject.getJSONObject("rates")

                // Lặp qua tất cả các loại tiền
                val keys = rates.keys()
                val defaultImage = BitmapFactory.decodeResource(resources, R.drawable.no_image)

                while (keys.hasNext()) {
                    val currency = keys.next()
                    val rateValue = rates.getDouble(currency)

                    val tygia = Tygia()
                    tygia.type = currency
                    tygia.imageurl = "" // API này không trả ảnh
                    tygia.bitmap = defaultImage
                    tygia.muatienmat = rateValue.toString()
                    tygia.muack = "-" // Không có dữ liệu
                    tygia.bantuenmat = "-" // Không có dữ liệu
                    tygia.banck = "-" // Không có dữ liệu

                    ds.add(tygia)
                }

            } catch (ex: Exception) {
                Log.e("Lỗi", ex.toString())
            }
            return ds
        }

        override fun onPostExecute(result: ArrayList<Tygia>) {
            super.onPostExecute(result)
            myAdapter.clear()
            myAdapter.addAll(result)
        }
    }
}
