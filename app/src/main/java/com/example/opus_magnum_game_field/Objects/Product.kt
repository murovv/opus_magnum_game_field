package com.example.opus_magnum_game_field.Objects

import android.content.Context
import android.graphics.Bitmap

open class Product(
    context: Context,
    cost: Int,
    name: String,
    mainCellCoordinates: Array<Int>,
    rot: Int,
    numberOfCells: Int
) : Element(context, cost, name, mainCellCoordinates, rot, numberOfCells){
    var amount:Int? = null
}