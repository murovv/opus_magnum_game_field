package com.example.opus_magnum_game_field

import android.content.Context
import com.example.opus_magnum_game_field.Objects.Element

class Engine (context: Context){
    private var gameField = Array(9){Array<Element?>(7){null}}
    fun addElementToGameField(element: Element, x: Int, y: Int) {
        gameField[x][y] = element
    }
}