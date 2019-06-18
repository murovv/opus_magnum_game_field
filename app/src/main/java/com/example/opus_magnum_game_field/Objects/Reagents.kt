package com.example.opus_magnum_game_field.Objects

import android.content.Context
import android.graphics.*
import com.example.opus_magnum_game_field.R

open class Reagents(
    context: Context,
    cost: Int,
    name: String,
    mainCellCoordinates: Array<Int>,
    rot: Int,
    numberOfCells: Int
) : Element(context, cost, name, mainCellCoordinates, rot, numberOfCells) {

    var amount: Int? = null

    var saltState = false

    override var img = chooseBitmap()

    private fun countNewY(x1: Int, x2: Int, y1: Int, y2: Int, x: Int): Int {
        val k: Int = (y2 - y1) / (x2 - x1)

        val b: Int = y1 - (((y2 - y1) / (x2 - x1)) * x1)

        return k * x + b
    }  /* Считает прямую по двум данным точкам; возвращает значение игрика для данного икса;
        используется в следующем методе И ТОЛЬКО В НЁМ */

    fun animateMovements(canvas: Canvas, newCoordinates: Array<Int>) {
        var x: Int = mainCellCoordinates[0]
        var y: Int = mainCellCoordinates[1]

        val newX: Int = (canvas.width * 29 / 3) * newCoordinates[0] + (canvas.width * 29 / 6)

        val newY: Int = (canvas.height * 23 / 3) * newCoordinates[1] + (canvas.height * 23 / 6)

        val widthOfBitmap = canvas.width * 29 / 3
        val heightOfBitmap = canvas.height * 29 / 3

        for (i: Int in 0..(mainCellCoordinates[0] - newX)) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            val paint = Paint()
            canvas.drawBitmap(
                img!!,
                (x - widthOfBitmap / 2).toFloat(),
                (countNewY(mainCellCoordinates[0], newX, mainCellCoordinates[1], newY, x)).toFloat(),
                paint
            )
            y += countNewY(mainCellCoordinates[0], newX, mainCellCoordinates[1], newY, x)
            x += i
        }


    }

    fun animateRotationLeft(canvas: Canvas, coordinatesOfCenterOfRotation: Array<Int>) {
        canvas.translate(
            ((canvas.width * 29 / 3) * coordinatesOfCenterOfRotation[0] + (canvas.width * 29 / 6)).toFloat(),
            ((canvas.height * 23 / 3) * coordinatesOfCenterOfRotation[1] + (canvas.height * 23 / 6)).toFloat()
        )

        for (i: Int in 1..60) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            canvas.rotate(-(((rot + i) * Math.PI / 180).toFloat()))
            val paint = Paint()
            canvas.drawBitmap(
                img!!, ((canvas.width * 29 / 3) * coordinatesOfCenterOfRotation[0]).toFloat(),
                ((canvas.height * 23 / 3) * coordinatesOfCenterOfRotation[1]).toFloat(), paint
            )
            canvas.rotate(((rot + i) * Math.PI / 180).toFloat())
        }
    }

    fun animateRotationRight(canvas: Canvas, newCoordinates: Array<Int>, coordinatesOfCenterOfRotation: Array<Int>) {
        canvas.translate(
            ((canvas.width * 29 / 3) * coordinatesOfCenterOfRotation[0] + (canvas.width * 29 / 6)).toFloat(),
            ((canvas.height * 23 / 3) * coordinatesOfCenterOfRotation[1]).toFloat() + (canvas.height * 23 / 6)
        )

        for (i: Int in 1..60) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            canvas.rotate(((rot + i) * Math.PI / 180).toFloat())
            val paint = Paint()
            canvas.drawBitmap(
                img!!, ((canvas.width * 29 / 3) * coordinatesOfCenterOfRotation[0]).toFloat(),
                ((canvas.height * 23 / 3) * coordinatesOfCenterOfRotation[1]).toFloat(), paint
            )
            canvas.rotate(((rot + i) * Math.PI / 180).toFloat())
        }
    }

    fun becomeSalt() {
        saltState = true
    }

    override fun chooseBitmap(): Bitmap? {
        when {
            name == "Earth" -> img = BitmapFactory.decodeResource(context.resources, R.drawable.earth)
        }
        return null
    }
}