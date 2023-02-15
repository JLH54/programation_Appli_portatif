package com.example.labo1

import android.os.Bundle
import com.example.labo1.main
import android.content.Intent
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class main2 : AppCompatActivity() {
    private val myButton: Button = findViewById(R.id.SwitchButton)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myButton.setOnClickListener{
            val intent = Intent(this, main::class.java)
            startActivity(intent)
        }
    }
}