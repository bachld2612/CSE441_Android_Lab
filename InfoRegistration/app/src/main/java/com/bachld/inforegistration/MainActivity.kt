package com.bachld.inforegistration

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var inputName: EditText
    lateinit var inputIdNumber: EditText
    lateinit var cbDocbao: CheckBox
    lateinit var cbDocSach: CheckBox
    lateinit var cbDocCoding: CheckBox
    lateinit var inputMoreInfo: EditText
    lateinit var btnGuiThongTin: Button
    lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inputName = findViewById(R.id.input_name)
        inputIdNumber = findViewById(R.id.input_id_number)
        cbDocbao = findViewById(R.id.cb_doc_bao)
        cbDocSach = findViewById(R.id.cb_doc_sach)
        cbDocCoding = findViewById(R.id.cb_doc_coding)
        inputMoreInfo = findViewById(R.id.input_more_info)
        btnGuiThongTin = findViewById(R.id.btn_gui_thong_tin)
        radioGroup = findViewById(R.id.radio_group_degree)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnGuiThongTin.setOnClickListener {
            if(validateInput()){
                showDialog()
            }
        }
        onBackPressedDispatcher.addCallback(this) {
            val dialog = AlertDialog.Builder(this@MainActivity)
                .setTitle("Xác nhận thoát")
                .setIcon(R.drawable.ic_launcher_background)
                .setMessage("Bạn có chắc chắn muốn thoát ứng dụng?")
                .setPositiveButton("Có") { _, _ -> finish() }
                .setNegativeButton("Không") { dialog, _ -> dialog.cancel() }
                .create()
                .show()
        }
    }

    fun validateInput(): Boolean{
        val name = inputName.text.toString().trim()
        if(name.length < 3){
            inputName.requestFocus()
            inputName.selectAll()
            Toast.makeText(this, "Tên phải dài ít nhất 3 ký tự", Toast.LENGTH_SHORT).show()
            return false
        }
        val idNumber = inputIdNumber.text.toString().trim()
        if(idNumber.length != 9){
            inputIdNumber.requestFocus()
            inputIdNumber.selectAll()
            Toast.makeText(this, "Số CMND/CCCD phải từ 9 đến 12 ký tự", Toast.LENGTH_SHORT).show()
            return false
        }
        for(number in idNumber){
            if(!number.isDigit()){
                inputIdNumber.requestFocus()
                inputIdNumber.selectAll()
                Toast.makeText(this, "Số CMND/CCCD chỉ được chứa chữ số", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }

    fun showDialog() {
        var id = radioGroup.checkedRadioButtonId
        var degree = findViewById<RadioButton>(id).text.toString()
        if(id == -1) {
            Toast.makeText(this, "Vui lòng chọn trình độ học vấn", Toast.LENGTH_SHORT).show()
            return
        }
        var soThich: String = ""
        if(cbDocbao.isChecked) {
            soThich += "Đọc báo, "
        }
        if(cbDocSach.isChecked) {
            soThich += "Đọc sách, "
        }
        if(cbDocCoding.isChecked) {
            soThich += "Đọc coding, "
        }
        val dialog = AlertDialog.Builder(this)
            .setTitle("Thông tin cá nhân")
            .setMessage("Tên: ${inputName.text}\n" +
                        "Số CMND/CCCD: ${inputIdNumber.text}\n" +
                        "Trình độ học vấn: $degree\n" +
                        "Sở thích: $soThich\n" +
                        "Thông tin thêm: ${inputMoreInfo.text}\n" +
                        "--------------")
            .setPositiveButton("Đóng") { dialog, _ -> dialog.cancel() }
            .create()
            .show()

    }



}