package com.bachld.karaoketab

import android.app.Activity
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
        val inflater = context.layoutInflater
        val rowView = convertView ?: inflater.inflate(layoutId, parent, false)

        val myItem = myArray[position]

        val btnLike = rowView.findViewById<ImageView>(R.id.btnlike)
        if (myItem.thich == 1) {
            btnLike.setImageResource(R.drawable.like)
        } else {
            btnLike.setImageResource(R.drawable.unlike)
        }

        val txtTieude = rowView.findViewById<TextView>(R.id.txttieude)
        txtTieude.text = myItem.tieude

        val txtMaso = rowView.findViewById<TextView>(R.id.txtmaso)
        txtMaso.text = myItem.maso

        return rowView
    }
}