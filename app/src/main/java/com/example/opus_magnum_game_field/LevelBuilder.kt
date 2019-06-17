package com.example.opus_magnum_game_field

import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.opus_magnum_game_field.Objects.Manipulator

class LevelBuilder(var lvlNum:Int,var resources: Resources) {
    fun getManipulators(){
        var manipulators = ArrayList<Manipulator>()
        var xrp: XmlResourceParser = resources.getXml(R.xml.manipulators)

    }
}