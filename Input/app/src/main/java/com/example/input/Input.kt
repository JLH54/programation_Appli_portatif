package com.example.input

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
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
import androidx.appcompat.widget.AppCompatButton
import java.lang.Float.max
import java.util.*
import kotlin.random.Random

//class MyLayout(context: Context?) : LinearLayout(context), OnReceiveContentListener{
//    val MIME_TYPES = arrayOf("image/*", "video/*")
//
//    init{
//        this.setBackgroundColor(resources.getColor(R.color.black))
//        this.orientation = LinearLayout.HORIZONTAL
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
//            setOnReceiveContentListener(MIME_TYPES, this)
//        }
//    }
//
//    override fun onReceiveContent(p0: View, p1: ContentInfo): ContentInfo? {
//        if(p1.clip.description.hasMimeType("image/*")){
//            var clipData = p1.clip.getItemAt(0)
//            var resId:Int? = null
//            if(clipData.uri.scheme.contentEquals("android.ressource")){
//                resId = clipData.uri.lastPathSegment?.toInt()
//            }
//
//            val imageView = ImageView(context)
//            resId?.let { imageView.setImageResource(it) }
//            val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            this.addView(imageView,layoutParams)
//            requestLayout()
//            return p1
//        }
//        return null
//    }
//}

class MyButton(context:Context) : AppCompatButton(context){
    private val particleRadius = 50f
    private val particlePaint = Paint().apply { color = Color.RED }
    private val particles = mutableListOf<Particle>()
    private var clickX = 0f;
    private var clickY = 0f;

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN){
            clickX = event.x
            clickY = event.y
        }


        return super.onTouchEvent(event)


    }

    override fun performClick(): Boolean {
        generateParticles()
        invalidate()
        return super.performClick()
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)

        for (particle in particles){
            canvas.save()

            canvas.translate(particle.x,particle.y)

            canvas.rotate(particle.rot)

            canvas.scale(1.5f,1.5f)

            canvas.drawRect(0f, 0f, particle.radius,particle.radius, particlePaint)

            canvas.restore()
        }

        updateParticles()

        particles.removeAll{ !it.isVisible()

        }

        if(particles.isNotEmpty()){
            postInvalidateOnAnimation()
        }
    }

    private fun updateParticles(){
        for(particle in particles){
            particle.x += particle.vx
            particle.y += particle.vy
            particle.rot += 10.0f
            particle.vx += Random.nextFloat() * 2f - 1f
            particle.vx += Random.nextFloat() * 2f - 1f
            particle.radius = max(0f,particle.radius-0.5f)
        }
    }

    private fun generateParticles(){
        val location = IntArray(2)
        getLocationOnScreen(location)
        val x = clickX
        val y = clickY

        for(i in 0 .. 20){
            particles.add(Particle(x, y,particleRadius))
        }
    }

    private data class Particle(
        var x:Float,
        var y:Float,
        var radius:Float,
        var vx:Float = 0f,
        var vy:Float= 0f,
        var rot:Float = 0f)
    {
        fun isVisible() = radius > 0f
    }
}

class Input : AppCompatActivity(){
    //private lateinit var myLayout:MyLayout
    private lateinit var linearLayout: LinearLayout
    private lateinit var addButton: MyButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL

//        myLayout = MyLayout(this)
//        myLayout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        addButton = MyButton(this)
        addButton.text = "Animate"
        //addButton.setOnClickListener{
//            val startX = it.scaleX
//            val startY = it.scaleY
//            it.animate()
//                .scaleX(0.5f)
//                .scaleY(0.5f)
//                .withEndAction{
//                    it.animate().scaleX(startX).scaleY(startY).duration = 250
//                }.duration = 250
        //}
        //imageView.setImageResource(R.drawable.ic_launcher_background)

        //linearLayout.addView(imageView, linearLayout.indexOfChild(addButton) + 1)
//        imageView.setOnTouchListener { view, motionEvent ->
//                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
//                    val imageUri =
//                        Uri.parse("android.res://${"com.example.input"}/${R.drawable.ic_launcher_background}")
//                    val clipDescription = ClipDescription("image", arrayOf("image/png"))
//                    val clipData = ClipData(clipDescription, ClipData.Item(imageUri))
//                    val shadowBuilder = View.DragShadowBuilder(view)
//                    view.startDragAndDrop(clipData, shadowBuilder, view, 0)
//                    (view.parent as ViewGroup).removeView(view)
//                    true
//                } else {
//                    false
//                }
//            }
//        }
        linearLayout.addView(addButton)
        //linearLayout.addView(myLayout)
        setContentView(linearLayout)
    }
}