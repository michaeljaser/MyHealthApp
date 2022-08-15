package com.example.myhealthapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pressure_readings.*
import kotlinx.android.synthetic.main.activity_sugar_readings.*
import kotlinx.android.synthetic.main.activity_sugar_readings.txtSugarReadings
import kotlinx.android.synthetic.main.activity_weight_readings.*

class WeightReadings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_readings)

        var result = contentResolver?.query(UserProvider.TABLE2_URI,
            arrayOf(UserProvider.COL2_DATE,
                UserProvider.COL2_WEIGHT),
            null,
            null,
            null
        )
        if(result?.moveToFirst()!!)
        {
            txtWeightReadings.text = ""

            do{
                txtWeightReadings.append(result?.getString(0)+"      "+ result?.getString(1)+"\n")
            }while (result.moveToNext())
        }


        weightBack.setOnClickListener() {
            startActivity(Intent(this@WeightReadings, MainActivity::class.java))
        }

    }
}