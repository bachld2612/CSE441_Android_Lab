package com.bachld.customlayout

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyArrayAdapter(
    private val context: Activity,
    private val idlayout: Int,
    private val mylist: ArrayList<Phone>
) : ArrayAdapter<Phone>(context, idlayout, mylist) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView: View
        val holder: ViewHolder

        if (convertView == null) {
            rowView = LayoutInflater.from(context).inflate(idlayout, parent, false)
            holder = ViewHolder(
                imgphone = rowView.findViewById(R.id.imgphone),
                txtnamephone = rowView.findViewById(R.id.txtnamephone)
            )
            rowView.tag = holder
        } else {
            rowView = convertView
            holder = rowView.tag as ViewHolder
        }

        val myphone = mylist[position]
        holder.imgphone.setImageResource(myphone.imagephone)
        holder.txtnamephone.text = myphone.namephone

        return rowView
    }

    private data class ViewHolder(
        val imgphone: ImageView,
        val txtnamephone: TextView
    )
}