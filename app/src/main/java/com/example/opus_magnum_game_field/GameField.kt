package com.example.opus_magnum_game_field

import android.content.Intent
import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup
import android.widget.*


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
fun listSizeCheck( listView: ListView): LinearLayout.LayoutParams {
    var params:LinearLayout.LayoutParams
    if (listView.adapter.count>2){
        var item = listView.adapter.getView(0, null, listView)
        item.measure(0, 0)
      params = LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, (2.5 * item.measuredHeight).toInt())
    }else{
        var item = listView.adapter.getView(0, null, listView)
        item.measure(0, 0)
        params= LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0)

    }

    return params

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

        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr)
        val voidAdapter =ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Array(1,{i->i.toString()}))
        reagents.setOnClickListener {
            reagentsList.adapter = adapter
            manipulatorList.adapter = voidAdapter
            operatorList.adapter = voidAdapter
            productList.adapter = voidAdapter
            reagentsList.setLayoutParams(listSizeCheck(reagentsList))
            operatorList.setLayoutParams(listSizeCheck(operatorList))
            manipulatorList.setLayoutParams(listSizeCheck(manipulatorList))
            productList.setLayoutParams(listSizeCheck(productList))
        }
        operators.setOnClickListener {
            reagentsList.adapter = voidAdapter
            manipulatorList.adapter = voidAdapter
            operatorList.adapter = adapter
            productList.adapter = voidAdapter
            reagentsList.setLayoutParams(listSizeCheck(reagentsList))
            operatorList.setLayoutParams(listSizeCheck(operatorList))
            manipulatorList.setLayoutParams(listSizeCheck(manipulatorList))
            productList.setLayoutParams(listSizeCheck(productList))
        }
        manipulators.setOnClickListener {
            reagentsList.adapter = voidAdapter
            manipulatorList.adapter = adapter
            operatorList.adapter = voidAdapter
            productList.adapter = voidAdapter
            reagentsList.setLayoutParams(listSizeCheck(reagentsList))
            operatorList.setLayoutParams(listSizeCheck(operatorList))
            manipulatorList.setLayoutParams(listSizeCheck(manipulatorList))
            productList.setLayoutParams(listSizeCheck(productList))
        }
        product.setOnClickListener {
            reagentsList.adapter = voidAdapter
            manipulatorList.adapter = voidAdapter
            operatorList.adapter = voidAdapter
            productList.adapter = adapter
            reagentsList.setLayoutParams(listSizeCheck(reagentsList))
            operatorList.setLayoutParams(listSizeCheck(operatorList))
            manipulatorList.setLayoutParams(listSizeCheck(manipulatorList))
            productList.setLayoutParams(listSizeCheck(productList))
        }
        operatorList.setOnItemClickListener{parent, view, position, id ->
            var textView: TextView = view as TextView

            choosen_item.text = textView.text.toString()

        }

    }

    var check = 100
}
