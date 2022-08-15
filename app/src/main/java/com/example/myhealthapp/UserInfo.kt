package com.example.myhealthapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_history2.*
import kotlinx.android.synthetic.main.activity_new_reading.*
import kotlinx.android.synthetic.main.activity_user_info2.*

class UserInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info2)

        var result = contentResolver?.query(
            UserProvider.TABLE1_URI,
            arrayOf(UserProvider.COL_FNM,
                UserProvider.COL_LNM,
                UserProvider.COL_DOB,
                UserProvider.COL_WEIGHT,
                UserProvider.COL_HEIGHT,
                UserProvider.COL_EMAIL),
            null,
            null,
            null
        )
        if(result?.moveToFirst()!!)
        {
            btnUserUpdate.isVisible = true
            btnUserSave.isVisible = false
            txtFirstName.setText(result.getString(0))
            txtLastName.setText(result.getString(1))
            txtDOB.setText(result.getString(2))
            txtWeight.setText(result.getString(3))
            txtHeight.setText(result.getString(4))
            txtEmail.setText(result.getString(5))
            txtFirstName.isEnabled=false
            txtLastName.isEnabled=false
        }
        else
        {
            btnUserUpdate.isVisible = false
            btnUserSave.isVisible = true
        }

        btnUserBack.setOnClickListener {
            startActivity(Intent(this@UserInfo, MainActivity::class.java))
        }


        btnUserSave.setOnClickListener {
            var cv = ContentValues()
            cv.put(UserProvider.COL_FNM, txtFirstName.text.toString())
            cv.put(UserProvider.COL_LNM, txtLastName.text.toString())
            cv.put(UserProvider.COL_DOB, txtDOB.text.toString())
            cv.put(UserProvider.COL_WEIGHT, txtWeight.text.toString().toInt())
            cv.put(UserProvider.COL_HEIGHT, txtHeight.text.toString().toInt())
            cv.put(UserProvider.COL_EMAIL, txtEmail.text.toString())
            contentResolver.insert(UserProvider.TABLE1_URI, cv)
            Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
        }
        btnUserUpdate.setOnClickListener {
            var cv = ContentValues()
            cv.put(UserProvider.COL_DOB, txtDOB.text.toString())
            cv.put(UserProvider.COL_WEIGHT, txtWeight.text.toString().toInt())
            cv.put(UserProvider.COL_HEIGHT, txtHeight.text.toString().toInt())
            cv.put(UserProvider.COL_EMAIL, txtEmail.text.toString())
            var no = contentResolver.update(UserProvider.TABLE1_URI, cv, "fname = ?", arrayOf(txtFirstName.text.toString()))
            if(no >0)
                Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "Nothing is Updated!!", Toast.LENGTH_SHORT).show()
        }
    }
}