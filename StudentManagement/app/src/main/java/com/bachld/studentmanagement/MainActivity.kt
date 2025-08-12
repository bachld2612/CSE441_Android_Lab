package com.bachld.studentmanagement

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    lateinit var edtmalop: EditText
    lateinit var edttenlop: EditText
    lateinit var edtsiso: EditText
    lateinit var btninsert: Button
    lateinit var btndelete: Button
    lateinit var btnupdate: Button
    lateinit var btnquery: Button
    lateinit var lv: ListView
    lateinit var arrayList: ArrayList<String>
    lateinit var adapter: ArrayAdapter<String>
    lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        edtmalop = findViewById(R.id.edtmalop)
        edttenlop = findViewById(R.id.edttenlop)
        edtsiso = findViewById(R.id.edtsiso)
        btninsert = findViewById(R.id.btninsert)
        btndelete = findViewById(R.id.btndelete)
        btnupdate = findViewById(R.id.btnupdate)
        btnquery = findViewById(R.id.btnquery)
        lv = findViewById(R.id.lv)
        arrayList = ArrayList()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
        lv.adapter = adapter
        db = openOrCreateDatabase("QLSV.db", MODE_PRIVATE, null)
        try{
            val query: String = "CREATE TABLE tbllop(malop TEXT primary key,tenlop TEXT, siso INTEGER)"
            db.execSQL(query)
        }catch (e: Exception){
            e.printStackTrace()
        }
        btninsert.setOnClickListener {
            val malop = edtmalop.text.toString()
            val tenlop = edttenlop.text.toString()
            val siso = edtsiso.text.toString().toIntOrNull() ?: 0
            val myValue: ContentValues = ContentValues().apply {
                put("malop", malop)
                put("tenlop", tenlop)
                put("siso", siso)
            }
            var message: String = ""
            if(db.insert("tbllop", null, myValue) == -1L) {
                message = "Thêm không thành công"
            } else {
                message = "Thêm thành công"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        btndelete.setOnClickListener {
            val malop = edtmalop.text.toString()
            var message: String = ""
            val n = db.delete("tbllop", "malop = ?", arrayOf(malop))
            if(n == 0) {
                message = "Xóa không thành công"
            } else {
                message = "Xóa thành công"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        btnupdate.setOnClickListener {
            val siso = edtsiso.text.toString().toIntOrNull() ?: 0
            val malop = edtmalop.text.toString()
            val contentValues: ContentValues = ContentValues().apply {
                put("siso", siso)
            }
            val n = db.update("tbllop", contentValues, "malop = ?", arrayOf(malop))
            var message: String = ""
            if(n == 0) {
                message = "Cập nhật không thành công"
            } else {
                message = "Cập nhật thành công"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        btnquery.setOnClickListener {
            arrayList.clear()
            val cursor = db.query("tbllop", null, null, null, null, null, null)
            cursor.moveToNext()
            var data: String
            while (!cursor.isAfterLast){
                data = "Mã lớp: ${cursor.getString(0)} - Tên lớp: ${cursor.getString(1)} - Sĩ số: ${cursor.getInt(2)}"
                arrayList.add(data)
                cursor.moveToNext()
            }
            cursor.close()
            adapter.notifyDataSetChanged();
        }
    }
}