package com.example.opus_magnum_game_field

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


enum class ReagentsName{
    //какие реагенты
}

open class Reagent(var name:ReagentsName, var amount:Int){
    fun getImage(name: ReagentsName){
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drop.setOnClickListener{
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)
            gameField.setImageBitmap(bitmap)
        }
    }
    var check = 100
}
