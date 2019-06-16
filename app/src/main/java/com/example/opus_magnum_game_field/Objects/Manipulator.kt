package com.example.opus_magnum_game_field.Objects

import android.graphics.Bitmap
import android.graphics.Canvas

class Manipulator(cost: Int, img: Bitmap, name: String, mainCellCoordinates: Array<Int>, rot: Int) :
    Element(cost, img, name, mainCellCoordinates, rot, numberOfCells = 2) {
    var takenElement:Element? = null
    var coordinateOfEar = coordinates!![1]
    val startRotation = rot
    fun rotateLeft(canvas: Canvas){
        rot += 60
        countCoordinates()
        coordinateOfEar = coordinates!![1]
        if (takenElement != null) {
            takenElement!!.setMainCellCoordinates(coordinateOfEar)
            takenElement!!.countCoordinates()
        }
        animateRotateLeft(canvas)
    }
    fun rotateRight(canvas: Canvas){
        rot -= 60
        countCoordinates()
        coordinateOfEar = coordinates!![1]
        if (takenElement != null) {
            takenElement!!.setMainCellCoordinates(coordinateOfEar)
            takenElement!!.countCoordinates()
        }
        animateRotateRight(canvas)
    }
    fun grab(element: Element?){
        takenElement = element
    }
    fun drop():Element?{
        val buffer = takenElement
        takenElement = null
        return buffer
    }
    fun returnToStart(canvas: Canvas){
        rot = startRotation
        countCoordinates()
        coordinateOfEar = coordinates!![1]
        if (takenElement != null) {
            takenElement!!.setMainCellCoordinates(coordinateOfEar)
            takenElement!!.countCoordinates()
        }
        returnToStart(canvas)
    }
    private fun animateRotateLeft(canvas: Canvas){
        //TODO Реализовать анимацию поворота налево без коллизий
    }
    private fun animateRotateRight(canvas: Canvas){
        //TODO Реализовать анимацию поворота направо без коллизий
    }
    private fun animationReturn(canvas: Canvas){
        //TODO Реализовать анимацию возвращения к изначальной позиции без коллизий
    }
}