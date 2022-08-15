package com.example.myhealthapp

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.widget.Toast

class UserProvider : ContentProvider(){

    companion object{
        /*val PROVIDER_NM = "com.example.myhealthapp/UserProvider"
        val URL = "content://$PROVIDER_NM/Users"
        val TABLE1_URI = Uri.parse(URL)*/

        val TABLE1_URI = Uri.parse("content://com.example.myhealthapp/UserProvider/Users")
        val TABLE2_URI = Uri.parse("content://com.example.myhealthapp/UserProvider/healthReadings")

        val COL_ID = "id"
        val COL_FNM = "fname"
        val COL_LNM = "lname"
        val COL_DOB = "dob"
        val COL_WEIGHT = "weight"
        val COL_HEIGHT = "height"
        val COL_EMAIL = "email"

        val COL2_ID = "id"
        val COL2_DATE = "date"
        val COL2_PRESSURE = "pressure"
        val COL2_SUGAR = "sugar"
        val COL2_WEIGHT = "weight"
    }

    lateinit var db : SQLiteDatabase


    override fun onCreate(): Boolean {
        var myDBHandler = DBHandler(context)
        db = myDBHandler.writableDatabase
        return if(db==null) false else true
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        if(uri == UserProvider.TABLE1_URI){
            db.insert("Users", null, cv)
        }
        if(uri == UserProvider.TABLE2_URI){
            db.insert("healthReadings", null, cv)
        }
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun delete(uri: Uri, condition: String?, condition_val: Array<out String>?): Int {
        var count = 0
        if(uri == UserProvider.TABLE1_URI){
            count = db.delete("Users", condition, condition_val)
        }
        if(uri == UserProvider.TABLE2_URI){
            count = db.delete("healthReadings", condition, condition_val)
        }
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun update(uri: Uri, cv: ContentValues?, condition: String?, condition_val: Array<out String>?): Int {
        var count = 0
        if(uri == UserProvider.TABLE1_URI){
            count = db.update("Users", cv, condition, condition_val)
        }
        if(uri == UserProvider.TABLE2_URI){
            count = db.update("healthReadings", cv, condition, condition_val)
        }

        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        order: String?
    ): Cursor? {
        if(uri == UserProvider.TABLE1_URI){
            return db.query("Users", cols,condition, condition_val, null, null, order)
        }
        if(uri == UserProvider.TABLE2_URI){
            return db.query("healthReadings", cols,condition, condition_val, null, null, order)
        }
        return null
    }

    override fun getType(p0: Uri): String? {
        return "vnd.android.cursor.dir/vnd.example.Users"
    }
}