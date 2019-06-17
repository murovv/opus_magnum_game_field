package com.example.opus_magnum_game_field

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.main_menu.*

class MainMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE //horizontal app
        background_layout.background = BitmapDrawable(resources,BitmapFactory.decodeResource(resources,R.drawable.background))
        //НЕ ТРОЖЬ УБЬЕТ
        ///*
        quit_button.background = BitmapDrawable(resources,BitmapFactory.decodeResource(resources,R.drawable.buttons))
        donate_button.background = BitmapDrawable(resources,BitmapFactory.decodeResource(resources,R.drawable.buttons))
        credits_button.background = BitmapDrawable(resources,BitmapFactory.decodeResource(resources,R.drawable.buttons))
        profile_button.background = BitmapDrawable(resources,BitmapFactory.decodeResource(resources,R.drawable.buttons))
        choose_level.background = BitmapDrawable(resources,BitmapFactory.decodeResource(resources,R.drawable.buttons))
        continue_button.background = BitmapDrawable(resources,BitmapFactory.decodeResource(resources,R.drawable.buttons))
        settings_button.background = BitmapDrawable(resources,BitmapFactory.decodeResource(resources,R.drawable.buttons))//*/
        val width = Resources.getSystem().displayMetrics.widthPixels
        val height = Resources.getSystem().displayMetrics.heightPixels

        val variable = 0
        choose_level.setOnClickListener {
            val intent = Intent(this,LevelsChoose::class.java)
            startActivity(intent)
        }
        quit_button.setOnClickListener {
            finish()
        }

    }
}
