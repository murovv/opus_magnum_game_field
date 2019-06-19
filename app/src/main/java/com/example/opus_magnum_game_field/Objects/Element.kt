package com.example.opus_magnum_game_field.Objects

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import androidx.core.graphics.applyCanvas
import com.example.opus_magnum_game_field.R


open class Element(
    internal open val context: Context,
    internal open val cost: Int,
    internal open val name: String,
    internal open var mainCellCoordinates: Array<Int>,
    internal open var rot: Int = 30,
    internal open var numberOfCells: Int,
    internal open var img: Bitmap? = null,
    protected open var imgSecondCell: Bitmap? = null){
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
        chooseBitmap()
    }
    open fun setMainCellCoordinates(mainCellCoordinates: Array<Int>) {
        this.mainCellCoordinates = mainCellCoordinates
    }
    fun countCoordinates():Array<Array<Int>>{
        coordinates = if(numberOfCells==1){
            arrayOf(mainCellCoordinates)
        } else {
            arrayOf(mainCellCoordinates, updateCoordinate(mainCellCoordinates, rot))
        }
        return coordinates!!
    }
    private fun updateCoordinate(coordinates:Array<Int>, rot: Int):Array<Int>{
        val resultCoordinates = Array(2){0}
        when {
            ((Math.cos(rot * Math.PI / 180) < 0.01)&&(Math.cos(rot * Math.PI / 180) > -0.01)) -> resultCoordinates[0] = mainCellCoordinates[0]
            Math.cos(rot * Math.PI / 180) > 0 -> resultCoordinates[0] = coordinates[0] + 1
            else -> resultCoordinates[0] = coordinates[0] - 1
        }
        when {
            Math.sin(rot * Math.PI / 180) == 1.0 -> resultCoordinates[1] = coordinates[1] - 1
            Math.sin(rot * Math.PI / 180) == -1.0 -> resultCoordinates[1] = coordinates[1] + 1
            (Math.sin(rot * Math.PI / 180) > 0) && (mainCellCoordinates[0] % 2 == 0) -> resultCoordinates[1] =
                coordinates[1] - 1
            (Math.sin(rot * Math.PI / 180) < 0) && (mainCellCoordinates[0] % 2 == 1) -> resultCoordinates[1] =
                coordinates[1] + 1
            else -> resultCoordinates[1] = coordinates[1]
        }
        return resultCoordinates
    }
    fun getimgSecondCell():Bitmap?{return imgSecondCell}
    open fun chooseBitmap(): Bitmap?{
        when {
            name == "Earth" -> {
                val bufferImg = BitmapFactory.decodeResource(context.resources, R.drawable.earth)
                img = Bitmap.createBitmap(bufferImg.width,bufferImg.height,Bitmap.Config.ARGB_8888)
                img!!.applyCanvas {
                    this.save()
                    //this.translate(this.width / 2.0F, this.height / 2.0F)
                    //this.rotate((-rot).toFloat())
                    this.drawBitmap(bufferImg,0.0F,0.0F, Paint())
                    //this.rotate(rot.toFloat())
                }
            }
            name == "Earth Product"->{
                val bufferImg = BitmapFactory.decodeResource(context.resources, R.drawable.water)
                img = Bitmap.createBitmap(bufferImg.width,bufferImg.height,Bitmap.Config.ARGB_8888)
                img!!.applyCanvas {
                    this.save()
                    this.drawBitmap(bufferImg, 0.0F, 0.0F, Paint())
                }
            }
            name == "Manipulator" -> {
                val bufferImg = BitmapFactory.decodeResource(context.resources, R.drawable.manipulator_base)
                img = Bitmap.createBitmap(bufferImg.width,bufferImg.height,Bitmap.Config.ARGB_8888)
                img!!.applyCanvas {
                    this.translate(this.width / 2.0F, this.height / 2.0F)
                    this.rotate((-rot).toFloat())
                    this.translate(-this.width / 2.0F, -this.height / 2.0F)
                    this.drawBitmap(bufferImg,0.0F,0.0F, Paint())
                    this.translate(this.width / 2.0F, this.height / 2.0F)
                    this.rotate(rot.toFloat())
                    this.translate(-this.width / 2.0F, -this.height / 2.0F)
                }
                val imgSecondCellBuffer = BitmapFactory.decodeResource(context.resources, R.drawable.manipulator_ring)
                imgSecondCell = Bitmap.createBitmap(bufferImg.width,bufferImg.height,Bitmap.Config.ARGB_8888)
                imgSecondCell!!.applyCanvas {
                    this.save()
                    this.translate(this.width / 2.0F, this.height / 2.0F)
                    this.rotate((-rot).toFloat())
                    this.translate(-this.width / 2.0F, -this.height / 2.0F)
                    this.drawBitmap(imgSecondCellBuffer,0.0F,0.0F, Paint())
                    this.translate(this.width / 2.0F, this.height / 2.0F)
                    this.rotate(rot.toFloat())
                    this.translate(-this.width / 2.0F, -this.height / 2.0F)
                }
            }
        }
        return null
    }
}