package com.example.opus_magnum_game_field

import android.content.Context
import com.example.opus_magnum_game_field.Objects.Element

class Engine (context: Context){
    private var gameField = Array(6){Array<Element?>(7){null}}
    fun addElementToGameField(element: Element?, x: Int, y: Int) {
        gameField[x][y] = element
    }
    fun getGameField():Array<Array<Element?>>{
        return gameField
    }
    fun detectTouc(width: Int, height: Int, x: Int, y: Int) {

    }
}