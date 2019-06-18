package com.example.opus_magnum_game_field

import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.opus_magnum_game_field.Objects.Element

import kotlinx.android.synthetic.main.activity_main.*
import android.widget.*
import com.example.opus_magnum_game_field.Objects.Reagents


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
    fun getReactive(name:String,resources: Resources){
        var xrp = resources.getXml(R.xml.lvl1)
        var cost:Int=0
        var numOfCells:Int = 0
        while(xrp.eventType!=XmlResourceParser.END_DOCUMENT){
            if(xrp.name=="Reagent" && xrp.getAttributeValue(null,"name")==name){
                val amount:Int = xrp.getAttributeValue(null,"amount").toInt()
                cost = xrp.getAttributeValue(null,"cost").toInt()
                numOfCells= xrp.getAttributeValue(null,"numOfCells").toInt()
                break
            }
            xrp.next()
        }
        var reagent: Reagents = Reagents(this,cost,name, Array(2) {0},30,numOfCells)
    }
    fun getManipulator(name:String,resources: Resources){

    }
    fun getProduct(){

    }
    fun getOperator(){

    }
    var engine: Engine? = null
    var graphicEngine:GraphicEngine = GraphicEngine()
    var chosenElement: Element? = null //TODO Нужно, чтобы эта переменная принимала значения элемента, соответствующего нажатой кнопке
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
        manipulatorList.setOnItemClickListener{parent, view, position, id->
            var textView: TextView = view as TextView
            choosen_item.text = textView.text.toString()
        }
        engine = Engine(this)


    }

    override fun onResume() {
        super.onResume()
        gameField.post {
            gameField.setOnClickListener {l: View ->
                if (chosenElement != null) {
                    chosenElement!!.mainCellCoordinates = Array(2) {
                        engine!!.detectTouch(gameField.width, gameField.height, (l.x.toInt()), l.y.toInt())[0]
                        engine!!.detectTouch(gameField.width, gameField.height, (l.x.toInt()), l.y.toInt())[1]
                    }
                    engine!!.addElementToGameField(
                        chosenElement,
                        engine!!.detectTouch(gameField.width, gameField.height, (l.x.toInt()), l.y.toInt())[0],
                        engine!!.detectTouch(gameField.width, gameField.height, (l.x.toInt()), l.y.toInt())[1])
                }
            }
        }
    }
    var check = 100
}
