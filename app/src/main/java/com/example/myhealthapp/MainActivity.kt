package com.example.myhealthapp

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mp : MediaPlayer
    var music :Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        var db = DBHandler(context)

        mp = MediaPlayer.create(this, R.raw.audio)


        clickListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.menuUserInfo)
        {
            startActivity(Intent(this@MainActivity, UserInfo::class.java))
        }
        if(item.itemId==R.id.menuSettings)
        {
            startActivity(Intent(this@MainActivity, Settings::class.java))
        }
        if(item.itemId==R.id.menuMusic)
        {
            if(music==0)
            {
                mp.start()
                music = 1
            }
            else if(music==1)
            {
                mp.pause()
                music = 0
            }
        }
        if(item.itemId==R.id.menuAboutUs)
        {
            var dialog = aboutUsDialog()
            dialog.show(supportFragmentManager, "about us")
        }

        return true
    }

    fun clickListener() {
        userInfo.setOnClickListener() {
            startActivity(Intent(this@MainActivity, UserInfo::class.java))
        }
        prevReadings.setOnClickListener() {
            startActivity(Intent(this@MainActivity, History::class.java))
        }
        newReading.setOnClickListener() {
            startActivity(Intent(this@MainActivity, NewReading::class.java))
        }
        pressure.setOnClickListener() {
            startActivity(Intent(this@MainActivity, PressureReadings::class.java))
        }
        crdSugar.setOnClickListener() {
            startActivity(Intent(this@MainActivity, SugarReadings::class.java))
        }
        crdWeight.setOnClickListener() {
            startActivity(Intent(this@MainActivity, WeightReadings::class.java))
        }
    }
}