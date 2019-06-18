package com.example.opus_magnum_game_field

import android.content.res.XmlResourceParser
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.opus_magnum_game_field.Objects.Element

import kotlinx.android.synthetic.main.activity_main.*
import android.widget.*



fun listSizeCheck( listView: ListView): LinearLayout.LayoutParams {
    var params:LinearLayout.LayoutParams
    if(listView.adapter.count==1&&listView.adapter.getItem(0).toString().toInt()==0){
        var item = listView.adapter.getView(0, null, listView)
        item.measure(0, 0)
        params= LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0)
    }
    else if (listView.adapter.count>2){
        var item = listView.adapter.getView(0, null, listView)
        item.measure(0, 0)
      params = LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, (2.5 * item.measuredHeight).toInt())
    }else{
        var item = listView.adapter.getView(0, null, listView)
        item.measure(0, 0)
        params= LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT )

    }

    return params
}
enum class ElementTypes{
    Reagent,Product,Operator,Manipulator
}

class MainActivity : AppCompatActivity() {
    fun getElement(name:String,type:ElementTypes): Element? {
        var xrp = resources.getXml(R.xml.lvl1)
        var cost:Int=0
        var numOfCells:Int = 0
        while(xrp.eventType!=XmlResourceParser.END_DOCUMENT){
            if(type==ElementTypes.Reagent && xrp.getAttributeValue(null,"name")==name){
                val amount:Int = xrp.getAttributeValue(null,"amount").toInt()
                cost = xrp.getAttributeValue(null,"cost").toInt()
                numOfCells= xrp.getAttributeValue(null,"numOfCells").toInt()
                break
            }
            if(type==ElementTypes.Manipulator && xrp.getAttributeValue(null,"name")==name){
                val amount:Int = xrp.getAttributeValue(null,"amount").toInt()
                cost = xrp.getAttributeValue(null,"cost").toInt()
                numOfCells= xrp.getAttributeValue(null,"numOfCells").toInt()
                break
            }
            if(type==ElementTypes.Operator && xrp.getAttributeValue(null,"name")==name){
                val amount:Int = xrp.getAttributeValue(null,"amount").toInt()
                cost = xrp.getAttributeValue(null,"cost").toInt()
                numOfCells= xrp.getAttributeValue(null,"numOfCells").toInt()
                break
            }
            if(type==ElementTypes.Product && xrp.getAttributeValue(null,"name")==name){
                val amount:Int = xrp.getAttributeValue(null,"amount").toInt()
                cost = xrp.getAttributeValue(null,"cost").toInt()
                numOfCells= xrp.getAttributeValue(null,"numOfCells").toInt()
                break
            }
            xrp.next()
        }
        return Element(this,cost,name, Array(2) {0},30,numOfCells)
    }

    var chosenElement: Element? = null //TODO Нужно, чтобы эта переменная принимала значения элемента, соответствующего нажатой кнопке

    val engine = Engine(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        fun updateLists(){
            reagentsList.setLayoutParams(listSizeCheck(reagentsList))
            operatorList.setLayoutParams(listSizeCheck(operatorList))
            manipulatorList.setLayoutParams(listSizeCheck(manipulatorList))
            productList.setLayoutParams(listSizeCheck(productList))
        }
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

        var reagentNames = ArrayList<String>()
        var operatorNames = ArrayList<String>()
        var productNames = ArrayList<String>()
        var manipulatorNames = ArrayList<String>()
        val xrp = resources.getXml(R.xml.lvl1)
        while(xrp.eventType!=XmlResourceParser.END_DOCUMENT){
            var elName = xrp.getAttributeValue(null,"name")
            if(xrp.name=="Reagent"&&elName!=null){
                reagentNames.add(elName.toString())
            }
            if(xrp.name=="Manipulator"&&elName!=null){
                manipulatorNames.add(elName.toString())
            }
            if(xrp.name=="Operator"&&elName!=null){
                operatorNames.add(elName.toString())
            }
            if(xrp.name=="Product"&&elName!=null){
                productNames.add(elName.toString())
            }
            xrp.next()
        }
        val prodAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,productNames)
        val opAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,operatorNames)
        val manAdapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,manipulatorNames)
        val reagAdapter =ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,reagentNames)
        val voidAdapter =ArrayAdapter<Int>(this,android.R.layout.simple_list_item_1, arrayOf(0))
        reagents.setOnClickListener {
            reagentsList.adapter = reagAdapter
            manipulatorList.adapter = voidAdapter
            operatorList.adapter = voidAdapter
            productList.adapter = voidAdapter
            updateLists()
        }
        operators.setOnClickListener {
            reagentsList.adapter = voidAdapter
            manipulatorList.adapter = voidAdapter
            operatorList.adapter = opAdapter
            productList.adapter = voidAdapter
            updateLists()
        }
        manipulators.setOnClickListener {
            reagentsList.adapter = voidAdapter
            manipulatorList.adapter = manAdapter
            operatorList.adapter = voidAdapter
            productList.adapter = voidAdapter
            updateLists()
        }
        product.setOnClickListener {
            reagentsList.adapter = voidAdapter
            manipulatorList.adapter = voidAdapter
            operatorList.adapter = voidAdapter
            productList.adapter = prodAdapter
            updateLists()
        }
        operatorList.setOnItemClickListener{parent, view, position, id ->
            var textView: TextView = view as TextView

            chosenElement = getElement(textView.text.toString(),ElementTypes.Operator)
            choosen_item.text = textView.text.toString()
        }
        manipulatorList.setOnItemClickListener{parent, view, position, id->
            var textView: TextView = view as TextView
            chosenElement = getElement(textView.text.toString(),ElementTypes.Manipulator)
            choosen_item.text = textView.text.toString()
        }
        productList.setOnItemClickListener{parent, view, position, id->
            var textView: TextView = view as TextView
            chosenElement = getElement(textView.text.toString(),ElementTypes.Product)
            choosen_item.text = textView.text.toString()
        }
        reagentsList.setOnItemClickListener{parent, view, position, id->
            var textView: TextView = view as TextView
            chosenElement = getElement(textView.text.toString(),ElementTypes.Reagent)
            choosen_item.text = textView.text.toString()
        }

        gameField.setOnClickListener {l: View ->
            if (chosenElement != null) {

            }
        }

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
