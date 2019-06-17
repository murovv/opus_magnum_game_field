package com.example.opus_magnum_game_field

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_levels_choose.*
import kotlinx.android.synthetic.main.activity_levels_choose.background_layout

class LevelsChoose : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels_choose)
        bt_lvl1.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        background_layout.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources,R.drawable.background))
        button_exit.setOnClickListener { finish() }
        bt_lvl1.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources,R.drawable.button1))
        //bt_lvl1.background = BitmapDrawable(resources,
        //   Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.buttons),bt_lvl1.width,bt_lvl1.height,false))
        //Log.d("MyOwnMessage", "Height of button ${button_layout.height}, width of button ${button_layout.width}")
    }
}
