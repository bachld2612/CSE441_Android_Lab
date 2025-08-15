package com.bachld.vnexpress

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView
import org.jsoup.Jsoup
import java.io.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var lv1: ListView
    }

    private lateinit var myList: ArrayList<List>
    private lateinit var myAdapter: MyArrayAdapter

    private val URL_RSS = "https://vnexpress.net/rss/tin-moi-nhat.rss"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lv1 = findViewById(R.id.lv1)
        myList = ArrayList()
        myAdapter = MyArrayAdapter(this, R.layout.layout_listview, myList)
        lv1.adapter = myAdapter

        LoadNewsTask().execute()
    }

    inner class LoadNewsTask : AsyncTask<Void, Void, ArrayList<List>>() {

        override fun onPreExecute() {
            super.onPreExecute()
            myAdapter.clear()
        }

        override fun doInBackground(vararg params: Void?): ArrayList<List> {
            val tempList = ArrayList<List>()
            try {
                // Kết nối tới RSS với User-Agent để tránh bị chặn
                val doc = Jsoup.connect(URL_RSS)
                    .userAgent("Mozilla/5.0")
                    .timeout(10000)
                    .get()

                val items = doc.select("item")
                for (item in items) {
                    val title = item.selectFirst("title")?.text() ?: ""
                    val link = item.selectFirst("link")?.text() ?: ""
                    val descriptionHtml = item.selectFirst("description")?.text() ?: ""

                    // Parse HTML trong description để lấy ảnh và text
                    val descDoc = Jsoup.parse(descriptionHtml)
                    val imgUrl = descDoc.selectFirst("img")?.attr("src") ?: ""
                    val descText = descDoc.text()

                    // Tải ảnh hoặc dùng ảnh mặc định
                    val bitmap: Bitmap = if (imgUrl.isNotEmpty()) {
                        try {
                            val url = URL(imgUrl)
                            BitmapFactory.decodeStream(url.openConnection().getInputStream())
                        } catch (e: IOException) {
                            BitmapFactory.decodeResource(resources, R.drawable.no_image)
                        }
                    } else {
                        BitmapFactory.decodeResource(resources, R.drawable.no_image)
                    }

                    tempList.add(List(bitmap, title, descText, link))
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return tempList
        }

        override fun onPostExecute(result: ArrayList<List>) {
            super.onPostExecute(result)
            myAdapter.clear()
            myAdapter.addAll(result)
        }
    }
}
