package com.example.opus_magnum_game_field

import android.content.Context
import com.example.opus_magnum_game_field.Objects.Element

class Engine(context: Context) {

    private var gameField = Array(6) { Array<Element?>(7) { null } }

    fun addElementToGameField(element: Element?, x: Int, y: Int) {
        gameField[x][y] = element
    }

    fun getGameField(): Array<Array<Element?>> {
        return gameField
    }

    private fun countCenterOfHexagon(width: Int, height: Int, coordinates: Array<Int>): Array<Int> {
        val centerX = (width * 29 / 3) * coordinates[0] + (width * 29 / 6)
        val centerY = (height * 23 / 3) * coordinates[1] + (height * 23 / 6)
        return Array(2) { centerX; centerY }
    }

    fun detectTouch(width: Int, height: Int, x: Int, y: Int): Array<Int> {

        var coordinatesGameField = Array(7, { IntArray(12) })

        for (i in 0 until coordinatesGameField.size) {
            var num = 0
            var rowArray = IntArray(12)

            for (j in 0 until rowArray.size) {
                rowArray[j] = num++
            }
            coordinatesGameField[i] = rowArray
        }


        var newX: Int = 0    //координата шестиугольника
        var newY: Int = 0    //координата шестиугольника



        for (i: Int in  0 until coordinatesGameField.size) {
            for (j: Int in 0 until coordinatesGameField[0].size) {
                val hexagon: Array<Int> = Array(2) {0}
                hexagon[0] = coordinatesGameField[i][j]
                hexagon[1] = coordinatesGameField[i][i]

                if (Math.pow((x - countCenterOfHexagon(width, height, hexagon)[0]).toDouble(), 2.0) +
                    Math.pow((y - countCenterOfHexagon(width, height, hexagon)[1]).toDouble(), 2.0) <=
                    Math.pow(((3 * width / 58) * Math.sqrt(3.0) / 2), 2.0)
                ) {
                    newX = hexagon[0]
                    newY = hexagon[1]
                    break
                }
            }
        }


        return Array(2) { newX; newY }


    }
}