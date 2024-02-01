package com.example.v1

import ImageAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2


class MainActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var imageAdapter: ImageAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intit()
        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)

            }
            })
    }
    override fun onPause(){
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override  fun onResume(){
        super.onResume()
        handler.postDelayed(runnable,2000)
    }
    private val runnable =Runnable{
        viewPager2.currentItem= viewPager2.currentItem + 1
    }

    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager2.setPageTransformer(transformer)
    }

    private fun intit() {
        viewPager2=findViewById(R.id.viewpager2)
        handler = Handler(Looper.getMainLooper()!!)
        imageList= ArrayList()
        imageList.add(R.drawable.house)
        imageList.add(R.drawable.house3)
        imageList.add(R.drawable.house4)
        imageList.add(R.drawable.house5)
        imageAdapter = ImageAdapter(imageList, viewPager2)
        viewPager2.adapter =imageAdapter
        viewPager2.offscreenPageLimit =3
        viewPager2.clipToPadding =false
        viewPager2.clipChildren=false
        viewPager2.getChildAt(0).overScrollMode= RecyclerView.OVER_SCROLL_NEVER

    }


}