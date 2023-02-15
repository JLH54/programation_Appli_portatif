package com.example.cours04

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class Test :AppCompatActivity(), View.OnClickListener {
    private val TAG:String = Test::class.java.simpleName;
    var linearLayout:LinearLayout? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        linearLayout = LinearLayout(this);
        var params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout?.orientation = LinearLayout.VERTICAL

        val button:Button = Button(this);
        button.setOnClickListener(this)
        button.setText("Change Activity");
        button.setLayoutParams(params);
        linearLayout?.addView(button)

        addContentView(linearLayout,params)
    }

    override fun onClick(p0: View?){
        val intent:Intent = Intent(this, activity2::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "OnStart");
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"onDestroy")
    }
}