package com.bachld.karaoke

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class MainActivity : AppCompatActivity() {

    private val DB_PATH_SUFFIX = "/databases/"
    companion object {
        var database: SQLiteDatabase? = null
        const val DATABASE_NAME = "arirang.sqlite"
    }

    private lateinit var edttim: EditText
    private lateinit var lv1: ListView
    private lateinit var lv2: ListView
    private lateinit var lv3: ListView
    private lateinit var btnxoa: ImageButton
    private lateinit var tab: TabHost

    private lateinit var list1: ArrayList<Item>
    private lateinit var list2: ArrayList<Item>
    private lateinit var list3: ArrayList<Item>
    private lateinit var myarray1: MyArrayAdapter
    private lateinit var myarray2: MyArrayAdapter
    private lateinit var myarray3: MyArrayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        processCopy()
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null)

        addControl()
        addTim()
        addEvents()
    }

    private fun addControl() {
        btnxoa = findViewById(R.id.btnxoa)
        tab = findViewById(android.R.id.tabhost)
        tab.setup()

        val tab1Spec = tab.newTabSpec("t1")
        tab1Spec.setContent(R.id.tab1)
        tab1Spec.setIndicator("", resources.getDrawable(R.drawable.search))
        tab.addTab(tab1Spec)

        val tab2Spec = tab.newTabSpec("t2")
        tab2Spec.setContent(R.id.tab2)
        tab2Spec.setIndicator("", resources.getDrawable(R.drawable.list))
        tab.addTab(tab2Spec)

        val tab3Spec = tab.newTabSpec("t3")
        tab3Spec.setContent(R.id.tab3)
        tab3Spec.setIndicator("", resources.getDrawable(R.drawable.favourite))
        tab.addTab(tab3Spec)

        edttim = findViewById(R.id.edttim)
        lv1 = findViewById(R.id.lv1)
        lv2 = findViewById(R.id.lv2)
        lv3 = findViewById(R.id.lv3)

        list1 = ArrayList()
        list2 = ArrayList()
        list3 = ArrayList()

        myarray1 = MyArrayAdapter(this, R.layout.listitem, list1)
        myarray2 = MyArrayAdapter(this, R.layout.listitem, list2)
        myarray3 = MyArrayAdapter(this, R.layout.listitem, list3)

        lv1.adapter = myarray1
        lv2.adapter = myarray2
        lv3.adapter = myarray3
    }

    private fun addEvents() {
        tab.setOnTabChangedListener { tabId ->
            when (tabId) {
                "t2" -> addDanhsach()
                "t3" -> addYeuthich()
            }
        }

        btnxoa.setOnClickListener {
            edttim.setText("")
        }
    }

    private fun addYeuthich() {
        myarray3.clear()
        val c: Cursor = database!!.rawQuery(
            "SELECT * FROM ArirangSongList WHERE YEUTHICH = 1",
            null
        )
        c.moveToFirst()
        while (!c.isAfterLast) {
            list3.add(Item(c.getString(1), c.getString(2), c.getInt(6)))
            c.moveToNext()
        }
        c.close()
        myarray3.notifyDataSetChanged()
    }

    private fun addDanhsach() {
        myarray2.clear()
        val c: Cursor = database!!.rawQuery("SELECT * FROM ArirangSongList", null)
        c.moveToFirst()
        while (!c.isAfterLast) {
            list2.add(Item(c.getString(1), c.getString(2), c.getInt(6)))
            c.moveToNext()
        }
        c.close()
        myarray2.notifyDataSetChanged()
    }

    private fun addTim() {
        edttim.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                getdata()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            private fun getdata() {
                val dulieunhap = edttim.text.toString()
                myarray1.clear()
                if (dulieunhap.isNotEmpty()) {
                    val c: Cursor = database!!.rawQuery(
                        "SELECT * FROM ArirangSongList WHERE TENBH1 LIKE '%$dulieunhap%' OR MABH LIKE '%$dulieunhap%'",
                        null
                    )
                    c.moveToFirst()
                    while (!c.isAfterLast) {
                        list1.add(Item(c.getString(1), c.getString(2), c.getInt(6)))
                        c.moveToNext()
                    }
                    c.close()
                }
                myarray1.notifyDataSetChanged()
            }
        })
    }

    private fun processCopy() {
        val dbFile = getDatabasePath(DATABASE_NAME)
        if (!dbFile.exists()) {
            try {
                copyDataBaseFromAsset()
                Toast.makeText(this, "Copying success from Assets folder", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getDatabasePath(): String {
        return applicationInfo.dataDir + DB_PATH_SUFFIX + DATABASE_NAME
    }

    private fun copyDataBaseFromAsset() {
        try {
            val myInput: InputStream = assets.open(DATABASE_NAME)
            val outFileName = getDatabasePath()
            val f = File(applicationInfo.dataDir + DB_PATH_SUFFIX)
            if (!f.exists()) f.mkdir()
            val myOutput: OutputStream = FileOutputStream(outFileName)
            val buffer = ByteArray(1024)
            var length: Int
            while (myInput.read(buffer).also { length = it } > 0) {
                myOutput.write(buffer, 0, length)
            }
            myOutput.flush()
            myOutput.close()
            myInput.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
