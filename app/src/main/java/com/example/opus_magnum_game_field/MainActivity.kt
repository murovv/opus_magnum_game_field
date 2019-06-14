package com.example.opus_magnum_game_field

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


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
    }
}
