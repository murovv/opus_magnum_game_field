package com.example.opus_magnum_game_field.Objects

import android.graphics.Bitmap

class Element(
    internal val cost: Int,
    internal val img: Bitmap,
    internal val name: String,
    internal var mainCellCoordinates: Array<Int>,
    internal var rot: Int,
    internal var numberOfCells: Int){
    internal var coordinates: Array<Array<Int>>? = null
    fun getCost(): Int {
        return cost
    }
    fun getImg(): Bitmap {
        return img
    }
    fun getName(): String {
        return name
    }
    fun getCoordinates(): Array<Array<Int>> {
        return coordinates!!
    }
    fun getMainCellCoordinates(): Array<Int> {
        return mainCellCoordinates
    }
    fun getRot(): Int {
        return rot
    }
    fun setRot(rot:Int){
        this.rot = rot
    }
    fun setMainCellCoordinates(mainCellCoordinates: Array<Int>) {
        this.mainCellCoordinates = mainCellCoordinates
    }
    fun countCoordinates(){
        coordinates = if(numberOfCells==1){
            Array(1){mainCellCoordinates}
        } else {
            Array(2){mainCellCoordinates; updateCoordinate(mainCellCoordinates, rot)}
        }
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