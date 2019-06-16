package com.example.opus_magnum_game_field

import android.content.Intent
import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

fun buildLevel1(resources:Resources): ArrayList<String> {
    var xrp:XmlResourceParser = resources.getXml(R.xml.lvl1)
    var reactives= ArrayList<String>()
    var products=ArrayList<String>()
    while (xrp.eventType!=XmlResourceParser.END_DOCUMENT){
        if(xrp.eventType==XmlResourceParser.START_TAG) {
            if (xrp.name == "Reagent") {
                xrp.next()
                reactives.add(xrp.text.toString())
            }
            if (xrp.name == "Product") {
                xrp.next()
                products.add(xrp.text.toString())
            }
        }
        Log.d("БЛЯТЬ","${xrp.name}")
        xrp.next()
    }
    return reactives
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)
        gameField.setImageBitmap(bitmap)
        drop.setOnClickListener{
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)
            gameField.setImageBitmap(bitmap)
        }
        exit.setOnClickListener{
            finish()
        }
        var arr = buildLevel1(resources)
        if(arr.isEmpty()){
            Log.d("БЛЯТЬ","ПИЗДЕЦ")
        }
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr)
        item_list.adapter = adapter
    }

    var check = 100
}
