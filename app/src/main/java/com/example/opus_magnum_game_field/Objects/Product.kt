package com.example.opus_magnum_game_field.Objects

import android.graphics.Bitmap

open class Product(
    cost: Int,
    img: Bitmap,
    name: String,
    mainCellCoordinates: Array<Int>,
    rot: Int,
    numberOfCells: Int
) : Element(cost, img, name, mainCellCoordinates, rot, numberOfCells){
    var amount:Int? = null
}