package com.example.bmi_calculation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var weight: EditText
    private  lateinit var height: EditText
    private  lateinit var obese: TextView
    private  lateinit var obeseDesc: TextView
    private  lateinit var info: TextView
    private  lateinit var calculateBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        init()
        calculateBtn.setOnClickListener {
            val weightVal=weight.text.toString()
            val heightVal=height.text.toString()
            if(validateInput(weightVal,heightVal)){
                val obm=weightVal.toFloat()/((heightVal.toFloat()/100)*(heightVal.toFloat()/100))
                val obm2D=String.format("%.2f",obm).toFloat()
                displayResult(obm2D)
            }

        }

    }
    private fun validateInput(weight:String,height:String):Boolean{
        when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this,"Weight is Empty",Toast.LENGTH_LONG).show()
                return  false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is Empty",Toast.LENGTH_LONG).show()
                return  false
            }
            else->{
                return true
            }
        }
    }
    private fun displayResult(obm:Float){
         obese.setText(obm.toString())
         info.setText("(Normal range 18.5-24.9)")
          var result=""
          var color=0
         when{
             obm<18.5->{
                 result="Under Weight"
                 color=R.color.under_weight
             }
             obm in 18.5..24.9->{
                 result="Healthy"
                 color=R.color.healthy
             }
             obm >24.9->{
                 result="Over Weight"
                 color=R.color.obese
             }
         }
        obeseDesc.setText(result)
        obeseDesc.setTextColor(ContextCompat.getColor(this,color))
    }
    private fun init(){
        weight=findViewById(R.id.weightVal)
        height=findViewById(R.id.heightVal)
        obese=findViewById(R.id.resultVal)
        calculateBtn=findViewById(R.id.CalcBtn)
        obeseDesc=findViewById(R.id.resultDesc)
        info=findViewById(R.id.BmiInfo)
    }
}