package com.example.opus_magnum_game_field.Objects

import android.content.Context
import android.graphics.Bitmap
enum class OperatorName{
    //TODO назвать все существующие операторы
    ROTATE_LEFT,ROTATE_RIGHT,GRAB,DROP,RETURN_TO_START,WAIT
}

open class Operators(
    context: Context,
    cost: Int,
    name: String,
    mainCellCoordinates: Array<Int>,
    rot: Int,
    numberOfCells: Int,
    img: Bitmap? = null
) : Element(context, cost, name, mainCellCoordinates, rot, numberOfCells, img = img) {


}