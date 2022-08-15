package com.example.myhealthapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DB_NAME = "HealthDB"

val TABLE1_NAME = "Users"
val COL1_ID = "id"
val COL1_FNAME = "fname"
val COL1_LNAME = "lname"
val COL1_DOB = "dob"
val COL1_WEIGHT = "weight"
val COL1_HEIGHT = "height"
val COL1_EMAIL = "email"

val TABLE2_NAME = "healthReadings"
val COL2_ID = "id"
val COL2_DATE = "date"
val COL2_PRESSURE = "pressure"
val COL2_SUGAR = "sugar"
val COL2_WEIGHT = "weight"



class DBHandler (var context: Context?) : SQLiteOpenHelper(context, DB_NAME, null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable1 = "CREATE TABLE " + TABLE1_NAME + " (" +
                COL1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL1_FNAME + " VARCHAR(25)," +
                COL1_LNAME + " VARCHAR(25)," +
                COL1_DOB + " VARCHAR(10)," +
                COL1_WEIGHT + " INTEGER," +
                COL1_HEIGHT + " INTEGER," +
                COL1_EMAIL + " VARCHAR(100))";

        db?.execSQL(createTable1)

        val createTable2 = "CREATE TABLE " + TABLE2_NAME + " (" +
                COL1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL2_DATE + " VARCHAR(25)," +
                COL2_PRESSURE + " VARCHAR(10)," +
                COL2_SUGAR + " INTEGER," +
                COL2_WEIGHT + " INTEGER)";

        db?.execSQL(createTable2)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(user: User){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL1_FNAME,user.fname)
        cv.put(COL1_LNAME,user.lname)
        cv.put(COL1_DOB, user.dob)
        cv.put(COL1_WEIGHT, user.weight)
        cv.put(COL1_HEIGHT, user.height)
        cv.put(COL1_EMAIL, user.email)
        var result = db.insert(TABLE1_NAME, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "INSERT FAILED: Please check input", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "INSERT SUCCESSFUL", Toast.LENGTH_SHORT).show()
    }

    fun updateUserData(user: User){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL1_FNAME,user.fname)
        cv.put(COL1_LNAME,user.lname)
        cv.put(COL1_DOB, user.dob)
        cv.put(COL1_WEIGHT, user.weight)
        cv.put(COL1_HEIGHT, user.height)
        cv.put(COL1_EMAIL, user.email)
        //db.beginTransaction()
        db.update(TABLE1_NAME, cv, "id=1", arrayOf())

      /*  if(result == (-1).toInt())
            Toast.makeText(context, "Update FAILED: Please check input", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Update SUCCESSFUL", Toast.LENGTH_SHORT).show()*/
       // db.endTransaction()
        db.close()
    }


    fun insertData(reading: Reading){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL2_DATE,reading.date)
        cv.put(COL2_PRESSURE,reading.pressure)
        cv.put(COL2_SUGAR, reading.sugar)
        cv.put(COL2_WEIGHT, reading.weight)
        var result = db.insert(TABLE2_NAME, null, cv)
        if(result == (-1).toLong())
            Toast.makeText(context, "INSERT FAILED: Please check input", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "INSERT SUCCESSFUL", Toast.LENGTH_SHORT).show()
    }
    fun readData() : MutableList<Reading>{
        var list : MutableList<Reading> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE2_NAME"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                var reading = Reading()
                reading.id = result.getString(0).toInt()
                reading.date = result.getString(1).toString()
                reading.pressure = result.getString(2).toString()
                reading.sugar = result.getString(3).toInt()
                reading.weight = result.getString(4).toInt()
                list.add(reading)
            }while (result.moveToNext())
        }
        result.close()
        db.close()

        return list
    }

    fun readUserData() : MutableList<User>{
        var list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE1_NAME"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do{
                var user = User()
                user.id = result.getString(0).toInt()
                user.fname = result.getString(1).toString()
                user.lname = result.getString(2).toString()
                user.dob = result.getString(3).toString()
                user.weight = result.getString(4).toInt()
                user.height = result.getString(5).toInt()
                user.email = result.getString(6).toString()
                list.add(user)
            }while (result.moveToNext())
        }
        result.close()
        db.close()

        return list
    }

    fun userExist() : Boolean{
        var exist:Boolean
        var list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from $TABLE1_NAME"
        val result = db.rawQuery(query, null)
        exist = result.moveToFirst()
        result.close()
        db.close()

        return exist
    }


    fun deleteData()
    {
        val db = this.writableDatabase
        db.execSQL("delete from $TABLE2_NAME");
        db.close()
    }

    fun deleteUserData()
    {
        val db = this.writableDatabase
        db.execSQL("delete from $TABLE1_NAME");
        db.close()
    }

}