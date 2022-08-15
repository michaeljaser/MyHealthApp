package com.example.myhealthapp

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.confirm_dialog.*

class Settings : AppCompatActivity() {
    var dialogDB=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        btnSettingsClearUser.setOnClickListener(){
            var dialog = confirmDialog()
            dialogDB="Users"

            var view = dialog.show(supportFragmentManager, "Confirm Delete")
        }

        btnSettingsClearHistory.setOnClickListener(){
            var dialog = confirmDialog()
            dialogDB="Readings"
            var view = dialog.show(supportFragmentManager, "Confirm Delete")
        }

    }
    fun recieveAction(action:String) {
        if (action == "Yes") {
            if (dialogDB == "Users")
            {
                val context = this
                var db = DBHandler(context)

                db.deleteUserData()
            }
            if (dialogDB == "Readings")
            {
                val context = this
                var db = DBHandler(context)

                db.deleteData()
            }
        }

    }
}