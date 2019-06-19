package com.example.opus_magnum_game_field

import android.content.Context
import com.example.opus_magnum_game_field.Objects.Element

class Engine(context: Context) {

    private var gameField = Array(12) { Array<Element?>(7) { null } }

    fun addElementToGameField(element: Element?, x: Int, y: Int) {
        gameField[x][y] = element
    }

    fun getGameField(): Array<Array<Element?>> {
        return gameField
    }

    private fun countCenterOfHexagon(widthOfCell: Int, heightOfCell: Int, coordinates: Array<Int>): Array<Int> {
        val centerX = if(coordinates[0] % 2 == 0){
            ((coordinates[0] / 2) * widthOfCell + (coordinates[0] / 2 + coordinates[0] % 2) * widthOfCell / 2 + widthOfCell / 2)
        } else{
            ((coordinates[0] / 2) * widthOfCell + (coordinates[0] / 2 + coordinates[0] % 2) * widthOfCell / 2 + widthOfCell / 4 + widthOfCell / 2)
        }
        val centerY = if (coordinates[0] % 2 == 0) {
            coordinates[1] * heightOfCell + heightOfCell
        } else {
            coordinates[1] * heightOfCell + heightOfCell / 2 + heightOfCell
        }
        return arrayOf(centerX, centerY)
    }

    fun detectTouch(width: Int, height: Int, x: Int, y: Int): Array<Int> {
        val widthOfCell = width * 3 / 29
        val heightOfCell = height * 3 / 23
        var newX: Int = 0    //координата шестиугольника
        var newY: Int = 0    //координата шестиугольника
        var flag = false
        for (i: Int in  0 until gameField.size) {
            for (j: Int in 0 until gameField[0].size) {
                val hexagon: Array<Int> = arrayOf(i,j)
                    if (Math.pow((x - countCenterOfHexagon(widthOfCell, heightOfCell, hexagon)[0]).toDouble(), 2.0) +
                    Math.pow((y - countCenterOfHexagon(widthOfCell, heightOfCell, hexagon)[1]).toDouble(), 2.0) <=
                    Math.pow(((3 * width / 58) * Math.sqrt(3.0) / 2), 2.0)
                ) {
                        newX = hexagon[0]
                        newY = hexagon[1]
                        flag = true
                        break
                }
            }
            if(flag){
                break
            }
        }


        return arrayOf(newX, newY)

    }
}