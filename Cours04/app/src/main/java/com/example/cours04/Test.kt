package com.example.cours04

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import java.io.InputStream
import java.lang.ClassCastException
import java.net.URL
import java.util.concurrent.Executors

class NameDialog : DialogFragment(){
    private lateinit var listener: NameDialogListener
    interface NameDialogListener{
        fun onNameEntered(name:String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            listener = context as NameDialogListener
        }
        catch (e:ClassCastException){
            throw ClassCastException("$context must implement NameDialogListener")
        }


    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Enter your name")

        val input = EditText(requireActivity())
        builder.setView(input)

        builder.setPositiveButton("OK") {_,_ ->
            listener.onNameEntered(input.text.toString())
        }
        builder.setNegativeButton("Cancel"){_,_ ->
            dialog?.cancel()
        }
        return builder.create()
    }

}

class Test :AppCompatActivity(), View.OnClickListener, NameDialog.NameDialogListener{
    private val TAG:String = Test::class.java.simpleName;
    var linearLayout:LinearLayout? = null;
    lateinit var counter: Counter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        linearLayout = LinearLayout(this);
        var params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
        val imageView = ImageView(this)
        imageView.layoutParams = params

//        val url = "https://upload.wikimedia.org/wikipedia/commons/0/01/LinuxCon_Europe_Linus_Torvalds_03_%28cropped%29.jpg"
//
//        val handler = Handler(Looper.getMainLooper())
//        val executor = Executors.newSingleThreadExecutor();
//        executor.execute{
//            try {
//                val inputStream:InputStream = URL(url).openStream()
//                val drawable = Drawable.createFromStream(inputStream, "random_src_name")
//                handler.post{
//                    imageView.setImageDrawable(drawable)
//                }
//            }
//            catch (e:Exception){
//                e.printStackTrace()
//            }
        //}
//        linearLayout?.orientation = LinearLayout.VERTICAL /
        counter = Counter(this)

        val button:Button = Button(this);
        button.setOnClickListener(this)
        button.setText("ADD");
        button.tag = "add"

        linearLayout?.addView(button)
        linearLayout?.addView(counter)
        addContentView(linearLayout,params)

    }

    override fun onClick(p0: View?){
        if(p0?.tag == "OpDialogue"){
            val dialog = NameDialog()
            dialog.show(supportFragmentManager, "NameDialog")
        }
        if(p0?.tag == "add"){
            counter.increment();
        }
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

    override fun onNameEntered(name: String) {
        Log.i(TAG, name)
    }
}