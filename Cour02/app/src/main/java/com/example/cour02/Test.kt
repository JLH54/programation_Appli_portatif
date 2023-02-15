package com.example.cour02

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.PI

open class Cercle(var rayon:Double){
    var aire:Double
    init{
        aire = rayon * rayon * PI
    }
    var halfRayon = 0.0;
    init{
        halfRayon = rayon / 2
    }
    constructor(i:Float) : this(i.toDouble()){

    }
}

class Pie(l:Double) : Cercle(l){

}






class Test : AppCompatActivity() {
    var yolo:String? = null
    lateinit var lol:String
    private val TAG:String = Test::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        var circle = Pie(2.0)
        yolo?.length



        if(::lol.isInitialized){

        }
        Log.d(TAG, "Hello world !");
        setContentView(R.layout.main)
        val image: ImageView = findViewById(R.id.imageView)
        //image.SetImageResource(R.drawable)
    }

    fun sum(a:Int,b:Int) {


    }
}