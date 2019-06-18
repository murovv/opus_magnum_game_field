package com.example.opus_magnum_game_field

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.example.opus_magnum_game_field.Objects.Element

class GraphicEngine {
    fun drawGameField(context : Context, canvas: Canvas, element:Array<Array<Element?>>){
        val widthOfCell = canvas.width * 3 / 29
        //val sizeOfSide = widthOfCell / 2
        val heightOfCell = canvas.height * 3 / 23
        canvas.drawBitmap(
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.resources, R.drawable.test),
                canvas.width,
                canvas.height,
                true
            ), 0.0F, 0.0F, Paint()
        )
        for(objects in element){
            for(elements in objects) {
                if (elements != null) {
                    canvas.drawBitmap(
                        Bitmap.createScaledBitmap(elements.img!!, widthOfCell, heightOfCell, true),
                        (elements.mainCellCoordinates[0].toFloat()), elements.mainCellCoordinates[1].toFloat(), Paint()
                    )
                    if (elements.numberOfCells == 2) {
                        canvas.drawBitmap(
                            Bitmap.createScaledBitmap(elements.getimgSecondCell()!!, widthOfCell, heightOfCell, true),
                            (elements.coordinates!![1][0].toFloat()), elements.coordinates!![1][1].toFloat(), Paint()
                        )
                    }
                }
            }
        }
    }
}