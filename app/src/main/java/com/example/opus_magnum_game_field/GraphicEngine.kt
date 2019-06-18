package com.example.opus_magnum_game_field

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import com.example.opus_magnum_game_field.Objects.Element

class GraphicEngine (){
    fun drawGameField(context : Context, canvas: Canvas, element:Array<Array<Element?>>){
        canvas.drawBitmap(
            Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(context.resources, R.drawable.test),
                canvas.width,
                canvas.height,
                true
            ), 0.0F, 0.0F, Paint()
        )
    }
}