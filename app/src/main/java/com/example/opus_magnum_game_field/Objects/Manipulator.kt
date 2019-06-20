package com.example.opus_magnum_game_field.Objects

import android.content.Context
import android.graphics.*
import android.util.Log
import com.example.opus_magnum_game_field.Engine
import com.example.opus_magnum_game_field.R
import java.time.temporal.TemporalAmount

class Manipulator(
    context: Context, cost: Int,
    img: Bitmap? = BitmapFactory.decodeResource(context.resources, R.drawable.manipulator_base),
    var engine: Engine,

    name: String, mainCellCoordinates: Array<Int>, rot: Int,
    imgSecondCell: Bitmap? = BitmapFactory.decodeResource(context.resources, R.drawable.manipulator_ring)) :
    Element(context, cost,name, mainCellCoordinates, rot, numberOfCells = 2, img = img, imgSecondCell = imgSecondCell) {

    var takenElement:Element? = null

    var coordinateOfEar = coordinates!![1]

    val startRotation = rot

    fun rotateLeft(canvas: Canvas) {
        //animateRotateLeft(canvas)

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
        //animateRotateRight(canvas)
    }

    fun grab() {
        val element = engine.getElement(coordinateOfEar[0],coordinateOfEar[1])
        takenElement = element
    }

    fun drop() {
        if ((takenElement != null) &&
            (engine.getGameField()[takenElement!!.mainCellCoordinates[0]][takenElement!!.mainCellCoordinates[1]] != null)) {
            if (engine.getGameField()[takenElement!!.mainCellCoordinates[0]][takenElement!!.mainCellCoordinates[1]]!!.name ==
                takenElement!!.name + " Product") {
                engine.win()
            }
        } else{
            val buffer = takenElement
            takenElement = null
            if (buffer != null) {
                engine.addElementToGameField(buffer, coordinateOfEar[0], coordinateOfEar[1])
            }
        }
    }

    fun returnToStart(canvas: Canvas,waitCalls:Int) {
        rot = startRotation
        countCoordinates()
        coordinateOfEar = coordinates!![1]
        if (takenElement != null) {
            takenElement!!.setMainCellCoordinates(coordinateOfEar)
            takenElement!!.countCoordinates()
        }
        Thread.sleep((1000*waitCalls).toLong())
    }

    private fun animateRotateLeft(canvas: Canvas) {
        canvas.translate(
            ((canvas.width * 3 / 29) * mainCellCoordinates[0] + (canvas.width * 3 / 58)).toFloat(),
            ((canvas.height * 3 / 23) * mainCellCoordinates[1] + (canvas.height * 3 / 46)).toFloat()
        )
        for (i: Int in 1..60) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            canvas.rotate(-(((rot + i) * Math.PI / 180).toFloat()))
            val paint = Paint()
            canvas.drawBitmap(
                img!!, ((canvas.width * 3 / 29) * mainCellCoordinates[0]).toFloat(),
                ((canvas.height * 3 / 23) * mainCellCoordinates[1]).toFloat(), paint
            )
            canvas.rotate(((rot + i) * Math.PI / 180).toFloat())
        }
    }

    private fun animateRotateRight(canvas: Canvas) {
        canvas.translate(
            ((canvas.width * 3 / 29) * mainCellCoordinates[0] + (canvas.width * 3 / 58)).toFloat(),
            ((canvas.height * 3 / 23) * mainCellCoordinates[1] + (canvas.height * 3 / 46)).toFloat()
        )
        for (i: Int in 1..60) {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            canvas.rotate(((rot + i) * Math.PI / 180).toFloat())
            val paint = Paint()
            canvas.drawBitmap(
                img!!, ((canvas.width * 3 / 29) * mainCellCoordinates[0]).toFloat(),
                ((canvas.height * 3 / 23) * mainCellCoordinates[1]).toFloat(), paint
            )
            canvas.rotate(((rot + i) * Math.PI / 180).toFloat())
        }
    }

    private fun animationReturn(canvas: Canvas) {
        //TODO Реализовать анимацию возвращения к изначальной позиции без коллизий
    }
    fun performAlgo(actions:ArrayList<OperatorName>,canvas: Canvas) {
        var waitCalls = 0
        for(action in actions){
            Log.d("MyLog","performer Called")
            if (action == OperatorName.ROTATE_LEFT) {
                rotateLeft(canvas)
            }
            if (action == OperatorName.ROTATE_RIGHT) {
                rotateRight(canvas)
            }
            if (action == OperatorName.DROP) {
                drop()
            }
            if (action == OperatorName.GRAB) {
                grab()
            }
            if (action == OperatorName.RETURN_TO_START) {
                returnToStart(canvas, waitCalls)
                waitCalls = 0
            }
            if (action == OperatorName.WAIT) {
                waitCalls++
            }
        }
    }

}