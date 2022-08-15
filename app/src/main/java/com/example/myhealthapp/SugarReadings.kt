package com.example.myhealthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_history2.*
import kotlinx.android.synthetic.main.activity_pressure_readings.*
import kotlinx.android.synthetic.main.activity_sugar_readings.*

class SugarReadings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugar_readings)

        var result = contentResolver?.query(UserProvider.TABLE2_URI,
            arrayOf(UserProvider.COL2_DATE,
                UserProvider.COL2_SUGAR),
            null,
            null,
            null
        )
        if(result?.moveToFirst()!!)
        {
            txtSugarReadings.text = ""

            do{
                txtSugarReadings.append(result?.getString(0)+"      "+ result?.getString(1)+"\n")
            }while (result.moveToNext())
        }

        sugarBack.setOnClickListener(){
            startActivity(Intent(this@SugarReadings, MainActivity::class.java))
        }
    }
}