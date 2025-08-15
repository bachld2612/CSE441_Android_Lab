package com.bachld.karaoke

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyArrayAdapter(
    private val context: Activity,
    private val layoutId: Int,
    private val myArray: ArrayList<Item>
) : ArrayAdapter<Item>(context, layoutId, myArray) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = context.layoutInflater
        val rowView = inflater.inflate(layoutId, null)

        val myItem = myArray[position]

        val tieude: TextView = rowView.findViewById(R.id.bttieude)
        val maso: TextView = rowView.findViewById(R.id.btnmaso)
        val btnlike: ImageView = rowView.findViewById(R.id.btnlike)
        val btnunlike: ImageView = rowView.findViewById(R.id.btnunlike)

        tieude.text = myItem.tieude
        maso.text = myItem.maso

        val thich = myItem.thich ?: 0
        if (thich == 0) {
            btnlike.visibility = View.INVISIBLE
            btnunlike.visibility = View.VISIBLE
        } else {
            btnunlike.visibility = View.INVISIBLE
            btnlike.visibility = View.VISIBLE
        }

        // Sự kiện like
        btnlike.setOnClickListener {
            val values = ContentValues()
            values.put("YEUTHICH", 0)
            MainActivity.database!!.update( // dùng !! để xác nhận không null
                "ArirangSongList",
                values,
                "MABH=?",
                arrayOf(maso.text.toString())
            )
            btnlike.visibility = View.INVISIBLE
            btnunlike.visibility = View.VISIBLE
        }

        // Sự kiện unlike
        btnunlike.setOnClickListener {
            val values = ContentValues()
            values.put("YEUTHICH", 1)
            MainActivity.database!!.update( // dùng !! để xác nhận không null
                "ArirangSongList",
                values,
                "MABH=?",
                arrayOf(maso.text.toString())
            )
            btnunlike.visibility = View.INVISIBLE
            btnlike.visibility = View.VISIBLE
        }

        // Click tiêu đề → đổi màu + mở ActivitySub
        tieude.setOnClickListener {
            tieude.setTextColor(Color.RED)
            maso.setTextColor(Color.RED)
            val intent = Intent(context, ActivitySub::class.java)
            val bundle = android.os.Bundle()
            bundle.putString("maso", maso.text.toString())
            intent.putExtra("package", bundle)
            context.startActivity(intent)
        }

        return rowView
    }
}