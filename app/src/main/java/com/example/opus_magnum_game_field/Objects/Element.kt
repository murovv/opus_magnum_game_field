package com.example.opus_magnum_game_field.Objects

import android.content.Context
import android.graphics.Bitmap


open class Element(
    internal open val context: Context,
    internal open val cost: Int,
    internal open val name: String,
    internal open var mainCellCoordinates: Array<Int>,
    internal open var rot: Int = 30,
    internal open var numberOfCells: Int,
    internal open var img: Bitmap? = null){
    internal open var coordinates: Array<Array<Int>>? = countCoordinates()

    open fun getCost(): Int {
        return cost
    }
    open fun getImg(): Bitmap? {
        return img
    }
    open fun getName(): String {
        return name
    }
    open fun getCoordinates(): Array<Array<Int>> {
        return coordinates!!
    }
    open fun getMainCellCoordinates(): Array<Int> {
        return mainCellCoordinates
    }
    open fun getRot(): Int {
        return rot
    }
    open fun setRot(rot:Int){
        this.rot = rot
    }
    open fun setMainCellCoordinates(mainCellCoordinates: Array<Int>) {
        this.mainCellCoordinates = mainCellCoordinates
    }
    open fun countCoordinates():Array<Array<Int>>{
        coordinates = if(numberOfCells==1){
            Array(1){mainCellCoordinates}
        } else {
            Array(2){mainCellCoordinates; updateCoordinate(mainCellCoordinates, rot)}
        }
        return coordinates!!
    }
    private fun updateCoordinate(coordinates:Array<Int>, rot: Int):Array<Int>{
        val resultCoordinates = Array(2){0}
        when {
            Math.cos(rot * Math.PI / 180) > 0 -> resultCoordinates[0] = coordinates[0] + 1
            Math.cos(rot * Math.PI / 180) == 0.0 -> resultCoordinates[0] = coordinates[0]
            else -> resultCoordinates[0] = coordinates[0] - 1
        }
        when {
            Math.sin(rot * Math.PI / 180) == 1.0 -> resultCoordinates[1] = coordinates[1] + 2
            Math.sin(rot * Math.PI / 180) == -1.0 -> resultCoordinates[1] = coordinates[1] - 2
            Math.sin(rot * Math.PI / 180) > 0 -> resultCoordinates[1] = coordinates[1] + 1
            else -> resultCoordinates[1] = coordinates[1] - 1
        }
        return resultCoordinates
    }
}