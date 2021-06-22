package com.beauty.mirrors.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.beauty.mirrors.Adapter.IntroSliderAdapter
import android.view.View
import com.beauty.mirrors.Fragments.Intro1Fragment
import com.beauty.mirrors.Fragments.Intro2Fragment
import com.beauty.mirrors.Fragments.Intro3Fragment
import com.beauty.mirrors.Fragments.Intro4Fragment
import com.beauty.mirrors.R
import kotlinx.android.synthetic.main.activity_slider.*
import java.util.*

class SliderActivity : AppCompatActivity() {
    private val fragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)


        val adapter = IntroSliderAdapter(this)
        vpIntroSlider.adapter = adapter
        fragmentList.addAll(listOf(Intro1Fragment(), Intro2Fragment(), Intro3Fragment(), Intro4Fragment()))
        adapter.setFragmentList(fragmentList)
        indicatorLayout.setIndicatorCount(adapter.itemCount)
        indicatorLayout.selectCurrentPosition(0)
        registerListeners()
    }
    private fun registerListeners() {
        vpIntroSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                indicatorLayout.selectCurrentPosition(position)
                if (position < fragmentList.lastIndex) {
                    tvSkip.visibility = View.VISIBLE
                    tvNext.text = "Next"
                } else {
                    tvSkip.visibility = View.GONE
                    tvNext.text = "LETS START"
                }
            }
        })
        tvSkip.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        tvNext.setOnClickListener {
            val position = vpIntroSlider.currentItem
            if (position < fragmentList.lastIndex) {
                vpIntroSlider.currentItem = position + 1
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}