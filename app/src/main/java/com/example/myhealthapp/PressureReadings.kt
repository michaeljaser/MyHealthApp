package com.example.myhealthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_history2.*
import kotlinx.android.synthetic.main.activity_pressure_readings.*

class PressureReadings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pressure_readings)

        var result = contentResolver?.query(UserProvider.TABLE2_URI,
            arrayOf(UserProvider.COL2_DATE,
                UserProvider.COL2_PRESSURE),
            null,
            null,
            null
        )
        if(result?.moveToFirst()!!)
        {
            txtPressureReadings.text = ""

            do{
                txtPressureReadings.append(result?.getString(0)+"      "+ result?.getString(1)+"\n")
            }while (result.moveToNext())
        }

        pressureBack.setOnClickListener(){
            startActivity(Intent(this@PressureReadings, MainActivity::class.java))
        }
    }
}