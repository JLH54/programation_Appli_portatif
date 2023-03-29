package com.example.input

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.media.Image
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MyLayout(context: Context?) : LinearLayout(context), OnReceiveContentListener{
    val MIME_TYPES = arrayOf("image/*", "video/*")

    init{
        this.setBackgroundColor(resources.getColor(R.color.black))
        this.orientation = LinearLayout.HORIZONTAL
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            setOnReceiveContentListener(MIME_TYPES, this)
        }
    }

    override fun onReceiveContent(p0: View, p1: ContentInfo): ContentInfo? {
        if(p1.clip.description.hasMimeType("image/*")){
            var clipData = p1.clip.getItemAt(0)
            var resId:Int? = null
            if(clipData.uri.scheme.contentEquals("android.ressource")){
                resId = clipData.uri.lastPathSegment?.toInt()
            }

            val imageView = ImageView(context)
            resId?.let { imageView.setImageResource(it) }
            val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            this.addView(imageView,layoutParams)
            requestLayout()
            return p1
        }
        return null
    }
}


class Input : AppCompatActivity(){
    private lateinit var myLayout:MyLayout
    private lateinit var linearLayout: LinearLayout
    private lateinit var addButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL

        myLayout = MyLayout(this)
        myLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        addButton = Button(this)
        addButton.text = "New Image"
        addButton.setOnClickListener{val imageView:ImageView = ImageView(this@Input)
        imageView.setImageResource(R.drawable.ic_launcher_background)

        linearLayout.addView(imageView, linearLayout.indexOfChild(addButton) + 1)
        imageView.setOnTouchListener { view, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    val imageUri =
                        Uri.parse("android.res://${"com.example.input"}/${R.drawable.ic_launcher_background}")
                    val clipDescription = ClipDescription("image", arrayOf("image/png"))
                    val clipData = ClipData(clipDescription, ClipData.Item(imageUri))
                    val shadowBuilder = View.DragShadowBuilder(view)
                    view.startDragAndDrop(clipData, shadowBuilder, view, 0)
                    (view.parent as ViewGroup).removeView(view)
                    true
                } else {
                    false
                }
            }
        }
        linearLayout.addView(addButton)
        linearLayout.addView(myLayout)
        setContentView(linearLayout)
    }
}