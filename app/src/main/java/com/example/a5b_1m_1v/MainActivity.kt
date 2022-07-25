package com.example.a5b_1m_1v

import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.a5b_1m_1v.adapter.PageAdapter
import com.example.a5b_1m_1v.model.Page
import me.relex.circleindicator.CircleIndicator3



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }
    fun initView(){

        var viewpager:ViewPager2=findViewById(R.id.viewpager)
        viewpager.orientation=ViewPager2.ORIENTATION_HORIZONTAL

        refreshAdapter(viewpager,getAllPage())

        val indecator: CircleIndicator3 = findViewById(R.id.indicator)
        indecator.setViewPager(viewpager)

        var skip:TextView=findViewById(R.id.skip)
        var started:TextView=findViewById(R.id.started)

        viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("Selected_Page", position.toString())

                if (position != 2) {
                    skip.visibility = View.VISIBLE
                    started.visibility = View.GONE
                } else {
                    skip.visibility = View.GONE
                    started.visibility = View.VISIBLE
                }
                skip.setOnClickListener {
                    if (position != 2) {
                        viewpager.currentItem=position+1
                    } else {
                        viewpager.currentItem=position
                    }
                }

            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

    }




    fun refreshAdapter(viewPager2: ViewPager2,pages:ArrayList<Page>){
        var adapter=PageAdapter(this,pages)
        viewPager2.adapter=adapter
    }

    fun getAllPage(): ArrayList<Page> {
        var pages = ArrayList<Page>()

        pages.add(
            Page(
                "a0.json",
                "  Say Hello to \nGlobal Top-Up",
                "Send mobile  top-up tomore than 500 networks     \n " +
                        "                        in over 140 countries"

            )
        )


        pages.add(
            Page(
                "a1.json",
                "Safe, Trusted & \n   Fully Secure ",
                "Encrypted transactions mean  your  payments & \n                       Privacy and protected"
            )
        )


        pages.add(
            Page(
                "a2.json",
                "Easy to Use",
                "Pick a number , choose an amount, send your \n                             Top-up. Simple"
            )
        )

        return pages
    }
}
