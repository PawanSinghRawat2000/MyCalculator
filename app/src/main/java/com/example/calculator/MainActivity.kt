package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var lastNumeric :Boolean=false
    var lastDot :Boolean=false
    var dotCount=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun onDigit(view: View) {
        turnTV.append((view as Button).text)
         lastNumeric =true


    }
    fun onClear(view: View) {
        turnTV.setText("")
        lastNumeric=false
        lastDot =false
        dotCount=0
    }
    fun onDot(view: View){
        if(lastNumeric && !lastDot &&dotCount==0){
            turnTV.append(".")
            dotCount=1
            lastNumeric=false
            lastDot=true
        }
    }
    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(turnTV.text.toString())){
            turnTV.append((view as Button).text)
            lastNumeric=false
            lastDot=false
            dotCount=0

        }

    }

    fun onEqual(view: View) {
        if(lastNumeric){
             var tvVal =turnTV.text.toString()
             var prefix=""
             try{
                 if(turnTV.text.startsWith("-")){
                     prefix="-"
                     tvVal=tvVal.substring(1)
                 }
                 if(tvVal.contains("-")){
                     val splitValue=tvVal.split("-")
                     var one=splitValue[0]
                     var two=splitValue[1]

                     if(!prefix.isEmpty()){
                         one=prefix+one
                    }
                     turnTV.text = removeZero((one.toDouble() - two.toDouble()).toString())

                 }

                 if(tvVal.contains("+")){
                     val splitValue=tvVal.split("+")
                     var one=splitValue[0]
                     var two=splitValue[1]

                     if(!prefix.isEmpty()){
                         one=prefix+one
                     }
                     turnTV.text = removeZero((one.toDouble() + two.toDouble()).toString())

                 }

                 if(tvVal.contains("*")){
                     val splitValue=tvVal.split("*")
                     var one=splitValue[0]
                     var two=splitValue[1]

                     if(!prefix.isEmpty()){
                         one=prefix+one
                     }
                     turnTV.text = removeZero((one.toDouble() * two.toDouble()).toString())

                 }

                 if(tvVal.contains("/")){
                     val splitValue=tvVal.split("/")
                     var one=splitValue[0]
                     var two=splitValue[1]

                     if(!prefix.isEmpty()){
                         one=prefix+one
                     }
                     turnTV.text = removeZero((one.toDouble() / two.toDouble()).toString())

                 }

             }catch (e: ArithmeticException){
                 e.printStackTrace()
             }
        }
    }

    private fun removeZero(result : String):String {
        var value=result
        if(result.contains(".0")){
            value=result.substring(0,result.length-2)
        }
        return value
    }

    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
             false
        }else{
            value.contains("/") || value.contains("*") || value.contains("+")
                    || value.contains("-")
        }
    }

    fun onBackspace(view: View) {
        var len = turnTV.length()
        if (len > 0) {
            turnTV.text = turnTV.text.substring(0, len - 1)
        }
    }

}