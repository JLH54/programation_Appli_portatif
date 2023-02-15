package com.example.labo1

import com.example.labo1.main2

import android.widget.*
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

class main : AppCompatActivity() {
    private val myButton: Button = findViewById(R.id.SwitchButton)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myButton.setOnClickListener{
            val intent = Intent(this, main2::class.java)
            startActivity(intent)
        }
    }

    //private val TAG:String = main::class.java.simpleName
//    var linearLayout:LinearLayout? = null
//    override fun Oncreate(savedInstanceState: Bundle?){
//        super.onCreate(savedInstanceState)
//        linearLayout = LinearLayout(this)
//
//        val button:Button = Button(this)
//        button.setText("This is a button.click me maybe")
//
//        linearLayout?.addView(button)
//
//        var layoutParams:LinearLayout.LayoutParams = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.WRAP_CONTENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        addContentView(linearLayout,layoutParams)
//    }


}