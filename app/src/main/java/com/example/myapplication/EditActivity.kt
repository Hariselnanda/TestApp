package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val _fistname : EditText = findViewById(R.id.et_first_name) as EditText
        val _lastName : EditText = findViewById(R.id.et_last_name) as EditText
        val _email : EditText = findViewById(R.id.et_email) as EditText
        val _dob : EditText = findViewById(R.id.et_dob) as EditText
        var bundle :Bundle ?=intent.extras
        _fistname.setText(bundle!!.getString("firstName"))
        _lastName.setText(bundle!!.getString("lastName"))
        _email.setText(bundle!!.getString("email"))
        _dob.setText(bundle!!.getString("dob"))

    }
}