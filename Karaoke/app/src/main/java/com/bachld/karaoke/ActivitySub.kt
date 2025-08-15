package com.bachld.karaoke

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivitySub : AppCompatActivity() {

    private lateinit var txtmaso: TextView
    private lateinit var txtbaihat: TextView
    private lateinit var txtloibaihat: TextView
    private lateinit var txttacgia: TextView
    private lateinit var btnthich: ImageButton
    private lateinit var btnkhongthich: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        // Ánh xạ view
        txtmaso = findViewById(R.id.btnmaso)
        txtbaihat = findViewById(R.id.bttieude)
        txtloibaihat = findViewById(R.id.txtloibaihat)
        txttacgia = findViewById(R.id.mbttacgia)
        btnthich = findViewById(R.id.btnthich)
        btnkhongthich = findViewById(R.id.btnkhongthich)

        // Nhận dữ liệu từ Adapter
        val bundle = intent.getBundleExtra("package")
        val maso = bundle?.getString("maso") ?: return
        txtmaso.text = maso

        // Truy vấn CSDL (dùng !! vì chắc chắn database đã được mở ở MainActivity)
        val c: Cursor = MainActivity.database!!.rawQuery(
            "SELECT * FROM ArirangSongList WHERE MABH LIKE '$maso'",
            null
        )
        if (c.moveToFirst()) {
            txtbaihat.text = c.getString(2)
            txtloibaihat.text = c.getString(3)
            txttacgia.text = c.getString(4)

            if (c.getInt(6) == 0) {
                btnthich.visibility = View.INVISIBLE
                btnkhongthich.visibility = View.VISIBLE
            } else {
                btnkhongthich.visibility = View.INVISIBLE
                btnthich.visibility = View.VISIBLE
            }
        }
        c.close()

        // Sự kiện khi click nút "Thích"
        btnthich.setOnClickListener {
            val values = ContentValues()
            values.put("YEUTHICH", 0)
            MainActivity.database!!.update(
                "ArirangSongList",
                values,
                "MABH=?",
                arrayOf(txtmaso.text.toString())
            )
            btnthich.visibility = View.INVISIBLE
            btnkhongthich.visibility = View.VISIBLE
        }

        // Sự kiện khi click nút "Không thích"
        btnkhongthich.setOnClickListener {
            val values = ContentValues()
            values.put("YEUTHICH", 1)
            MainActivity.database!!.update(
                "ArirangSongList",
                values,
                "MABH=?",
                arrayOf(txtmaso.text.toString())
            )
            btnkhongthich.visibility = View.INVISIBLE
            btnthich.visibility = View.VISIBLE
        }
    }
}
