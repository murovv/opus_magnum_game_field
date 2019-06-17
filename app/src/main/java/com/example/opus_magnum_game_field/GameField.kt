package com.example.opus_magnum_game_field

import android.content.Intent
import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.opus_magnum_game_field.Objects.Element
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

        xrp.next()
    }
    return reactives
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var chosenElement: Element? = null //TODO Нужно, чтобы эта переменная принимала значения элемента, соответствующего нажатой кнопке
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

        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr)
        item_list.adapter = adapter
        item_list.setOnItemClickListener{parent, view, position, id ->
            var textView: TextView = view as TextView
            choosen_item.text = textView.text.toString()
        }
        val engine = Engine(this)
        /*gameField.setOnClickListener {l: View ->
            if (chosenElement != null) {
                engine.addElementToGameField(chosenElement,l.x/gameField.)
            }
        }*/
        Log.i("Test", "" + gameField.width)
    }

    override fun onResume() {
        super.onResume()
        gameField.post {
            Log.d("Test", "width" + gameField.width)
            Log.d("Test", "height" + gameField.height)
        }
    }
    var check = 100
}
