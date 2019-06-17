package com.example.opus_magnum_game_field

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_levels_choose.*
import kotlinx.android.synthetic.main.activity_levels_choose.background_layout

class LevelsChoose : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels_choose)
        bt_lvl1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        background_layout.background =
            BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.background))
        button_exit.setOnClickListener { finish() }
        button_exit.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.buttons))
        button_settings.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.buttons))

        button_exit.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.buttons))
        button_settings.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.buttons))

        button_exit.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.buttons))
        button_settings.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.buttons))

        bt_lvl1.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.button1))
        bt_lvl2.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.bottles1))
        bt_lvl3.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.bottles2))
        bt_lvl4.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.bottles3))
        bt_lvl5.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.bottles4))
        bt_lvl6.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.bottles5))
        bt_lvl7.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.bottles6))
        bt_lvl8.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.bottles7))
        bt_lvl9.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.bottles8))
        bt_lvl10.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.bottles1))
        bt_lvl11.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.bottles2))
        bt_lvl12.background = BitmapDrawable(resources, BitmapFactory.decodeResource(resources, R.drawable.bottles3))

        //bt_lvl1.background = BitmapDrawable(resources,
        //   Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources,R.drawable.buttons),bt_lvl1.width,bt_lvl1.height,false))
        //Log.d("MyOwnMessage", "Height of button ${button_layout.height}, width of button ${button_layout.width}")
    }
}
