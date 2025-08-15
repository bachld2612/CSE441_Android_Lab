package com.bachld.vnexpress

import android.app.Activity
import android.widget.ArrayAdapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MyArrayAdapter(
    private val context: Activity,
    private val layoutID: Int,
    private val arr: ArrayList<List>
) : ArrayAdapter<List>(context, layoutID, arr) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater: LayoutInflater = context.layoutInflater
        val rowView = inflater.inflate(layoutID, null)

        val item = arr[position]

        val imgItem: ImageView = rowView.findViewById(R.id.imgView)
        imgItem.setImageBitmap(item.img)

        val txtTitle: TextView = rowView.findViewById(R.id.txtTitle)
        txtTitle.text = item.title

        val txtInfo: TextView = rowView.findViewById(R.id.txtInfo)
        txtInfo.text = item.info

        rowView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            context.startActivity(intent)
        }

        return rowView
    }
}
