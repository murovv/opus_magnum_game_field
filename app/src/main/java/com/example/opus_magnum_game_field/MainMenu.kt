package com.example.opus_magnum_game_field

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.MainMenu.*

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.MainMenu)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE //horizontal app

        val width = Resources.getSystem().displayMetrics.widthPixels
        val height = Resources.getSystem().displayMetrics.heightPixels

        val variable = 0
        choose_level.setOnClickListener {
            val intent = Intent(this,LevelsChoose::class.java)
            startActivity(intent)
        }
    }
}
