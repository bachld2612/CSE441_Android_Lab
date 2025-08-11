package com.bachld.customgridview

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var adapter: MyarrayAdapter
    private val items = arrayListOf<Image>()

    companion object {
        val arrayName = arrayOf(
            "Ảnh 1", "Ảnh 2", "Ảnh 3", "Ảnh 4", "Ảnh 5", "Ảnh 6",
            "Ảnh 7", "Ảnh 8", "Ảnh 9", "Ảnh 10", "Ảnh 11", "Ảnh 12"
        )

        val imageName = intArrayOf(
            R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8,
            R.drawable.img9, R.drawable.img10, R.drawable.img11, R.drawable.img12
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gridView = findViewById(R.id.grid1)

        // 1) Build data
        for (i in imageName.indices) {
            items.add(Image(img = imageName[i], name = arrayName[i]))
        }

        // 2) Adapter + set to GridView
        adapter = MyarrayAdapter(this, R.layout.listitem, items)
        gridView.adapter = adapter

        // 3) Item click -> mở SubActivity và truyền dữ liệu
        gridView.setOnItemClickListener { _, _, position, _ ->
            val item = items[position]
            val intent = Intent(this, SubActivity::class.java) // lưu ý tên class đúng
            intent.putExtra("title", item.name)
            intent.putExtra("imageRes", item.img)
            startActivity(intent)
        }
    }
}