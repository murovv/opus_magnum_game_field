package com.example.opus_magnum_game_field

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.XmlResourceParser
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.opus_magnum_game_field.Objects.Element

import kotlinx.android.synthetic.main.activity_main.*
import android.widget.*
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.drawable.toDrawable
import com.example.opus_magnum_game_field.Objects.Manipulator
import com.example.opus_magnum_game_field.Objects.OperatorName
import com.example.opus_magnum_game_field.Objects.Reagents



fun listSizeCheck( listView: ListView): LinearLayout.LayoutParams {
    val params:LinearLayout.LayoutParams
    if (listView.adapter.getItem(0).toString().matches(Regex("[0-9]*"))){

        if(listView.adapter.count==1&&listView.adapter.getItem(0).toString().toInt()==0){
            val item = listView.adapter.getView(0, null, listView)
            item.measure(0, 0)
            params= LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, 0)
        }else{
            params= LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT)
        }

    }
    else if (listView.adapter.count>2){
        val item = listView.adapter.getView(0, null, listView)
        item.measure(0, 0)
      params = LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, (2.5 * item.measuredHeight).toInt())
    }else{
        val item = listView.adapter.getView(0, null, listView)
        item.measure(0, 0)
        params= LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT,android.widget.LinearLayout.LayoutParams.WRAP_CONTENT )

    }

    return params
}
enum class ElementTypes{
    Reagent,Product,Operator,Manipulator
}

class MainActivity : AppCompatActivity() {
    private fun getElement(name:String, type:ElementTypes): Element? {
        val xrp = resources.getXml(R.xml.lvl1)
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
                return Manipulator(this,cost,null,engine,name, arrayOf(0,0),30,null)
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

    var chosenElement: Element? = null
    var graphicEngine:GraphicEngine = GraphicEngine()
    var engine = Engine(this)
    var actions =ArrayList<OperatorName>()
    override fun onCreate(savedInstanceState: Bundle?) {
        fun updateLists(){
            reagentsList.setLayoutParams(listSizeCheck(reagentsList))
            operatorList.setLayoutParams(listSizeCheck(operatorList))
            manipulatorList.setLayoutParams(listSizeCheck(manipulatorList))
            productList.setLayoutParams(listSizeCheck(productList))
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        val prodAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productNames)
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
            val textView: TextView = view as TextView

            chosenElement = getElement(textView.text.toString(),ElementTypes.Operator)
            choosen_item.text = textView.text.toString()
        }
        manipulatorList.setOnItemClickListener{parent, view, position, id->
            val textView: TextView = view as TextView
            chosenElement = getElement(textView.text.toString(),ElementTypes.Manipulator)
            choosen_item.text = textView.text.toString()
        }
        productList.setOnItemClickListener{parent, view, position, id->
            val textView: TextView = view as TextView
            chosenElement = getElement(textView.text.toString(),ElementTypes.Product)
            choosen_item.text = textView.text.toString()
        }
        reagentsList.setOnItemClickListener{parent, view, position, id->
            val textView: TextView = view as TextView
            chosenElement = getElement(textView.text.toString(),ElementTypes.Reagent)
            choosen_item.text = textView.text.toString()
        }

        var actionsStringNames = ArrayList<String>()

        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, actionsStringNames)
        manipulator1.adapter=adapter

        grab.setOnClickListener {
            actions.add(OperatorName.GRAB)
            actionsStringNames.add("grab")
            adapter.notifyDataSetChanged()
        }
        drop.setOnClickListener {
            actions.add(OperatorName.DROP)
            actionsStringNames.add("drop")
            adapter.notifyDataSetChanged()
        }
        rleft.setOnClickListener {
            actions.add(OperatorName.ROTATE_LEFT)
            actionsStringNames.add("rleft")
            adapter.notifyDataSetChanged()
        }
        rright.setOnClickListener {
            actions.add(OperatorName.ROTATE_RIGHT)
            actionsStringNames.add("rright")
            adapter.notifyDataSetChanged()
        }
        wait.setOnClickListener {
            actions.add(OperatorName.WAIT)
            actionsStringNames.add("wait")
            adapter.notifyDataSetChanged()
        }
        returnButton.setOnClickListener {
            actions.add(OperatorName.RETURN_TO_START)
            actionsStringNames.add("returnToStart")
            adapter.notifyDataSetChanged()
        }
        delete.setOnClickListener {
            if(actions.size!=0) {
                actions.removeAt(actions.size - 1)
                actionsStringNames.removeAt(actions.size - 1)
            }
        }

        deleteall.setOnClickListener {
            actions.clear()
            actionsStringNames.clear()
        }
        task.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Task")
            builder.setMessage("Перенесите Землю в Продукт при помощи манипулятора\n" +
                    "Задать алгоритм для манипулятора вы можете при помощи кнопок справа\n" +
                    "Ваш алгоритм отобразится в нижней части экрана")

            builder.setPositiveButton("ok") { dialog, listener->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }

    private var touchedElement: Element? = null

    override fun onResume() {
        super.onResume()
        var bitmap: Bitmap? = null
        gameField.post {
            val backgroundBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.test),
                gameField.width, gameField.height, true)
            gameField.setImageBitmap(backgroundBitmap)
            gameField.setOnTouchListener { view, motionEvent ->
                bitmap = Bitmap.createBitmap(gameField.width, gameField.height, Bitmap.Config.ARGB_8888)
                Log.i("Touch", "x = ${motionEvent.x.toInt()}, y = ${motionEvent.y.toInt()}")
                if (chosenElement != null) {
                    chosenElement!!.chooseBitmap()
                    chosenElement!!.mainCellCoordinates = arrayOf(
                        engine.detectTouch(gameField.width, gameField.height, (motionEvent.x.toInt()), motionEvent.y.toInt())[0],
                        engine.detectTouch(gameField.width, gameField.height, (motionEvent.x.toInt()), motionEvent.y.toInt())[1]
                    )
                    chosenElement!!.countCoordinates()
                    chosenElement!!.setAmountMinusOne()
                    engine.addElementToGameField(
                        chosenElement,
                        chosenElement!!.mainCellCoordinates[0], chosenElement!!.mainCellCoordinates[1], true
                    )

                } else if (engine.getGameField()
                            [engine.detectTouch(gameField.width, gameField.height, (motionEvent.x.toInt()), motionEvent.y.toInt())[0]]
                            [engine.detectTouch(gameField.width, gameField.height, (motionEvent.x.toInt()), motionEvent.y.toInt())[1]] != null) {
                    touchedElement = (engine.getGameField()
                            [engine.detectTouch(gameField.width, gameField.height, (motionEvent.x.toInt()), motionEvent.y.toInt())[0]]
                            [engine.detectTouch(gameField.width, gameField.height, (motionEvent.x.toInt()), motionEvent.y.toInt())[1]])
                }
                bitmap!!.applyCanvas {
                    graphicEngine.drawGameField(this@MainActivity, this, engine!!.getGameField())
                }
                gameField.setImageBitmap(bitmap!!)
                chosenElement = null
                true
            }
            fun updateGameField(){
                bitmap = Bitmap.createBitmap(gameField.width, gameField.height, Bitmap.Config.ARGB_8888)
                bitmap!!.applyCanvas {
                    graphicEngine.drawGameField(this@MainActivity, this, engine!!.getGameField())
                }
                gameField.setImageBitmap(bitmap!!)
            }
            start.setOnClickListener {
                for(manArr in engine.getGameField()){
                    for(man in manArr){
                        if((man!=null)&&man.name=="Manipulator"){
                            val manip = Manipulator(this, man.cost,null, engine, man.name,
                                man.mainCellCoordinates, man.rot, man.getimgSecondCell())
                            manip.performAlgo(actions, Canvas(), gameField, this)//ОПАСНО
                            manip.coordinates  = manip.countCoordinates()
                            manip.chooseBitmap()
                            Log.i("Manipulator state",""+man.rot)
                            engine.addElementToGameField(manip,man.getMainCellCoordinates()[0],man.getMainCellCoordinates()[1])
                            updateGameField()
                            if(engine.isWin){
                                val aboutDialog = AlertDialog.Builder(this).setMessage("You Win")
                                    .setPositiveButton("Return to menu") { _, _ ->
                                        finish()
                                    }.create()

                                aboutDialog.show()
                            }
                        }
                    }
                }
            }
            delete_object.setOnClickListener{
                if (touchedElement != null) {
                    engine.addElementToGameField(
                        null,
                        touchedElement!!.getMainCellCoordinates()[0],
                        touchedElement!!.getMainCellCoordinates()[1])
                    updateGameField()
                }
            }
        }

    }
    //Пасхальное ицо, не трохай
    var check = 100
}
