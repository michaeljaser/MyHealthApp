package com.example.myhealthapp

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_history2.*
import kotlinx.android.synthetic.main.activity_new_reading.*
import kotlinx.android.synthetic.main.activity_pressure_readings.*
import kotlinx.android.synthetic.main.activity_user_info2.*

class NewReading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_reading)

        btnReadingAdd.setOnClickListener {
            var cv = ContentValues()
            cv.put(UserProvider.COL2_DATE, txtReadingDate.text.toString())
            cv.put(UserProvider.COL2_PRESSURE, txtReadingPressure.text.toString())
            cv.put(UserProvider.COL2_SUGAR, txtReadingSugar.text.toString().toInt())
            cv.put(UserProvider.COL_WEIGHT, txtReadingWeight.text.toString().toInt())
            contentResolver.insert(UserProvider.TABLE2_URI, cv)
            Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
        }
        btnReadingSearch.setOnClickListener {
            var result = contentResolver?.query(UserProvider.TABLE2_URI,
                arrayOf(UserProvider.COL2_DATE,
                    UserProvider.COL2_PRESSURE,
                    UserProvider.COL2_SUGAR,
                    UserProvider.COL2_WEIGHT),
                " date = ? ",
                arrayOf(txtReadingDate.text.toString()),
                null
            )
            if(result?.moveToFirst()!!)
            {
                    txtReadingDate.setText(result?.getString(0))
                    txtReadingPressure.setText(result?.getString(1))
                    txtReadingSugar.setText(result?.getString(2))
                    txtReadingWeight.setText(result?.getString(3))
            }
        }
        btnReadingUpdate.setOnClickListener {
            if(txtReadingDate.text.toString().isNotEmpty())
            {
                var cv = ContentValues()
                cv.put(UserProvider.COL2_PRESSURE, txtReadingPressure.text.toString())
                cv.put(UserProvider.COL2_SUGAR, txtReadingSugar.text.toString().toInt())
                cv.put(UserProvider.COL2_WEIGHT, txtReadingWeight.text.toString().toInt())
                var no = contentResolver.update(UserProvider.TABLE2_URI, cv, "date = ?", arrayOf(txtReadingDate.text.toString()))
                if(no >0)
                    Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Nothing is Updated!!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "You need to enter the date!!", Toast.LENGTH_SHORT).show()
            }
        }

        btnReadingDelete.setOnClickListener {
            if(txtReadingDate.text.toString().isNotEmpty())
            {
                var no = contentResolver.delete(UserProvider.TABLE2_URI, "date = ?", arrayOf(txtReadingDate.text.toString()))
                if(no >0)
                    Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Nothing is Deleted!!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "You need to enter a date!!", Toast.LENGTH_SHORT).show()
            }
            clearFields()
        }
        btnReadingClear.setOnClickListener{
            clearFields()
        }
       /* val context = this
        var db = DBHandler(context)
        btnReadingSave.setOnClickListener {
            if (txtReadingDate.text.toString().isNotEmpty() &&
                txtReadingPressure.text.toString().isNotEmpty() &&
                txtReadingSugar.text.toString().isNotEmpty() &&
                txtReadingWeight.text.toString().isNotEmpty())
            {
                var reading = Reading(
                    txtReadingDate.text.toString(),
                    txtReadingPressure.text.toString(),
                    txtReadingSugar.text.toString().toInt(),
                    txtReadingWeight.text.toString().toInt()
                )

                db.insertData(reading)
            } else {
                Toast.makeText(context, "Please fill data properly", Toast.LENGTH_SHORT).show()
            }
        }*/
    }

    fun clearFields(){
        txtReadingDate.setText("")
        txtReadingPressure.setText("")
        txtReadingSugar.setText("")
        txtReadingWeight.setText("")
    }

}