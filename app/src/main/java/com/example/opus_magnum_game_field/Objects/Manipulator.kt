package com.example.opus_magnum_game_field.Objects

import android.graphics.*

class Manipulator(cost: Int, img: Bitmap, name: String, mainCellCoordinates: Array<Int>, rot: Int) :
    Element(cost, img, name, mainCellCoordinates, rot, numberOfCells = 2) {

    var takenElement: Element? = null

    var coordinateOfEar = coordinates!![1]

    val startRotation = rot

    fun rotateLeft(canvas: Canvas) {
        animateRotateLeft(canvas)
        rot += 60
        countCoordinates()
        coordinateOfEar = coordinates!![1]
        if (takenElement != null) {
            takenElement!!.setMainCellCoordinates(coordinateOfEar)
            takenElement!!.countCoordinates()
        }
    }

    fun rotateRight(canvas: Canvas) {
        rot -= 60
        countCoordinates()
        coordinateOfEar = coordinates!![1]
        if (takenElement != null) {
            takenElement!!.setMainCellCoordinates(coordinateOfEar)
            takenElement!!.countCoordinates()
        }
        animateRotateRight(canvas)
    }

    fun grab(element: Element?) {
        takenElement = element
    }

    fun drop(): Element? {
        val buffer = takenElement
        takenElement = null
        return buffer
    }

    fun returnToStart(canvas: Canvas) {
        rot = startRotation
        countCoordinates()
        coordinateOfEar = coordinates!![1]
        if (takenElement != null) {
            takenElement!!.setMainCellCoordinates(coordinateOfEar)
            takenElement!!.countCoordinates()
        }
        returnToStart(canvas)
    }

    private fun animateRotateLeft(canvas: Canvas) {
        canvas.translate(
            ((canvas.width * 29 / 3) * mainCellCoordinates[0] + (canvas.width * 29 / 6)).toFloat(),
            ((canvas.height * 23 / 3) * mainCellCoordinates[1]).toFloat() + (canvas.height * 23 / 6)
        )
        for (i: Int in 1..60) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            canvas.rotate(-(((rot + i) * Math.PI / 180).toFloat()))
            val paint = Paint()
            canvas.drawBitmap(
                img, ((canvas.width * 29 / 3) * mainCellCoordinates[0]).toFloat(),
                ((canvas.height * 23 / 3) * mainCellCoordinates[1]).toFloat(), paint
            )
            canvas.rotate(((rot + i) * Math.PI / 180).toFloat())
        }
    }

    private fun animateRotateRight(canvas: Canvas) {
        canvas.translate(
            ((canvas.width * 29 / 3) * mainCellCoordinates[0] + (canvas.width * 29 / 6)).toFloat(),
            ((canvas.height * 23 / 3) * mainCellCoordinates[1]).toFloat() + (canvas.height * 23 / 6)
        )
        for (i: Int in 1..60) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            canvas.rotate(((rot + i) * Math.PI / 180).toFloat())
            val paint = Paint()
            canvas.drawBitmap(
                img, ((canvas.width * 29 / 3) * mainCellCoordinates[0]).toFloat(),
                ((canvas.height * 23 / 3) * mainCellCoordinates[1]).toFloat(), paint
            )
            canvas.rotate(((rot + i) * Math.PI / 180).toFloat())
        }
    }

    private fun animationReturn(canvas: Canvas) {
        //TODO Реализовать анимацию возвращения к изначальной позиции без коллизий
    }
}