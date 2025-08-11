package com.bachld.customgridview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class MyarrayAdapter(
    private val context: Activity,
    private val layoutId: Int,
    private val myArray: ArrayList<Image>
) : ArrayAdapter<Image>(context, layoutId, myArray) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val row: View
        val holder: ViewHolder

        if (convertView == null) {
            row = LayoutInflater.from(context).inflate(layoutId, parent, false)
            holder = ViewHolder(
                row.findViewById(R.id.imageView1),
                row.findViewById(R.id.textView1)
            )
            row.tag = holder
        } else {
            row = convertView
            holder = row.tag as ViewHolder
        }

        val item = myArray[position]
        holder.img.setImageResource(item.img)
        holder.name.text = item.name

        return row
    }

    private data class ViewHolder(
        val img: ImageView,
        val name: TextView
    )
}