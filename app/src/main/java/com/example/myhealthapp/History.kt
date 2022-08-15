package com.example.myhealthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_history2.*
import kotlinx.android.synthetic.main.activity_user_info2.*

class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history2)

        var result = contentResolver?.query(UserProvider.TABLE2_URI,
            arrayOf(UserProvider.COL2_DATE,
                UserProvider.COL2_PRESSURE,
                UserProvider.COL2_SUGAR,
                UserProvider.COL2_WEIGHT),
            null,
            null,
            null
        )
        if(result?.moveToFirst()!!)
        {
            historyReadings.text = ""

            do{
                historyReadings.append(result?.getString(0)+"\n"+
                result?.getString(1)+"\n"+
                result?.getString(2)+"\n"+
                result?.getString(3)+"\n---\n")
            }while (result.moveToNext())
        }

        historyBack.setOnClickListener(){
            startActivity(Intent(this@History, MainActivity::class.java))
        }
        historyClear.setOnClickListener() {
            val context = this
            var db = DBHandler(context)

            db.deleteData()
        }
    }
}