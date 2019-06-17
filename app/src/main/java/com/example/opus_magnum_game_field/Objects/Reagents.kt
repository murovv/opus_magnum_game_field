package com.example.opus_magnum_game_field.Objects

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.example.opus_magnum_game_field.R

open class Reagents(
    context: Context,
    cost: Int,
    name: String,
    mainCellCoordinates: Array<Int>,
    rot: Int,
    numberOfCells: Int
) : Element(context, cost, name, mainCellCoordinates, rot, numberOfCells){
    var amount:Int? = null
    var saltState = false
    override var img = chooseBitmap()
    fun animateMovements(canvas: Canvas, newCoordinates: Array<Int>) {
        //TODO Доделать анимацию
    }

    fun animateRotationMovements(canvas: Canvas, newCoordinates: Array<Int>, coordinatesOfCenterOfRotation: Array<Int>){

    }

    fun becomeSalt(){
        saltState = true
    }

    fun chooseBitmap(): Bitmap? {
        when {
            name == "Earth" -> return BitmapFactory.decodeResource(context.resources, R.drawable.button1)
        }
        return null
    }
}