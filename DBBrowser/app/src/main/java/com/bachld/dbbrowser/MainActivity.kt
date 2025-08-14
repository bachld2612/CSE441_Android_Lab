package com.bachld.dbbrowser

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class MainActivity : AppCompatActivity() {

    private val DB_PATH_SUFFIX = "/databases/"
    private val DATABASE_NAME = "QLSV.db"
    private var database: SQLiteDatabase? = null

    private lateinit var lv: ListView
    private lateinit var mylist: ArrayList<String>
    private lateinit var myadapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        processCopy()

        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null)

        lv = findViewById(R.id.lv)
        mylist = ArrayList()
        myadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mylist)
        lv.adapter = myadapter

        val cursor = database?.query("tbllop", null, null, null, null, null, null)
        cursor?.apply {
            moveToFirst()
            while (!isAfterLast) {
                val data = "${getString(0)} - ${getString(1)} - ${getString(2)}"
                mylist.add(data)
                moveToNext()
            }
            close()
        }

        myadapter.notifyDataSetChanged()
    }

    private fun processCopy() {
        val dbFile = getDatabasePath(DATABASE_NAME)
        if (!dbFile.exists()) {
            try {
                copyDatabaseFromAssets()
                Toast.makeText(this, "Copying success from Assets folder", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getDatabasePathCustom(): String {
        return applicationInfo.dataDir + DB_PATH_SUFFIX + DATABASE_NAME
    }

    private fun copyDatabaseFromAssets() {
        try {
            val myInput: InputStream = assets.open(DATABASE_NAME)
            val outFileName = getDatabasePathCustom()

            val f = File(applicationInfo.dataDir + DB_PATH_SUFFIX)
            if (!f.exists()) f.mkdir()

            val myOutput: OutputStream = FileOutputStream(outFileName)

            val buffer = ByteArray(myInput.available())
            myInput.read(buffer)
            myOutput.write(buffer)

            myOutput.flush()
            myOutput.close()
            myInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
