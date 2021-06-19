package com.beauty.mirrors.Activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.beauty.mirrors.Helper.PrefManager
import com.beauty.mirrors.R
import java.util.*

class SliderActivity : AppCompatActivity() {
    val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed
    val PERIOD_MS: Long = 2000 // time in milliseconds between successive task executions.
    var timer: Timer? = null
    var currentPage = 0
    var app_continue_button: AppCompatButton? = null
    var btnSkip: Button? = null
    var btnNext:Button? = null
    var prefManager: PrefManager? = null

    //    lateinit var dots: Array<TextView>
    val dots: Array<TextView?>? = null

    var dotsLayout: LinearLayout? = null
    var myViewPagerAdapter: MyViewPagerAdapter? = null
    var viewPager: ViewPager? = null

    companion object {
        var layouts: IntArray = intArrayOf(R.layout.welcome_slide1, R.layout.welcome_slide2, R.layout.welcome_slide3, R.layout.welcome_slide4)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       /* prefManager = PrefManager(this);
        if (!prefManager!!.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }*/
        setContentView(R.layout.activity_slider)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.black_transparent));
        }

        viewPager = findViewById<android.view.View?>(R.id.view_pager) as ViewPager?
        dotsLayout = findViewById<android.view.View?>(R.id.layoutDots) as LinearLayout?
        btnSkip = findViewById<android.view.View?>(R.id.btn_skip) as android.widget.Button?
        btnNext = findViewById<android.view.View?>(R.id.btn_next) as android.widget.Button?

        app_continue_button = findViewById<View>(R.id.app_continue_button) as AppCompatButton
        app_continue_button!!.setOnClickListener {
            if (app_continue_button!!.text.toString().equals("LETS START", ignoreCase = true)) {
                launchHomeScreen()
            }
        }
        // layouts of all welcome sliders and add few more layouts if you want
        layouts = intArrayOf(R.layout.welcome_slide1, R.layout.welcome_slide2, R.layout.welcome_slide3, R.layout.welcome_slide4)
        // adding bottom dots
        addBottomDots(0)

        // making notification bar transparent
        //   changeStatusBarColor();
        myViewPagerAdapter = MyViewPagerAdapter()
        viewPager!!.adapter = myViewPagerAdapter
        viewPager!!.addOnPageChangeListener(viewPagerPageChangeListener)

        btnSkip!!.setOnClickListener { launchHomeScreen() }

        btnNext!!.setOnClickListener {
            // checking for last page and if last page home screen will be launched
            val current: Int = getItem(+1)
            if (current < layouts!!.size) {
                // move to next screen
                viewPager!!.currentItem = current
            } else {
                launchHomeScreen()
            }
        }

        /*After setting the adapter use the timer */
        val handler = Handler()
        val Update = Runnable {
            if (currentPage === layouts!!.size) {
                timer!!.purge()
            }
            viewPager!!.setCurrentItem(currentPage++, true)
        }

        timer = Timer()
        // This will create a new Thread

        timer!!.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)
    }
    private fun getItem(i: Int): Int {
        return viewPager!!.currentItem + i
    }


    private fun addBottomDots(currentPage: Int) {
//        dots =  arrayOfNulls[layouts?.size];
        val dots: Array<TextView?>? = layouts?.size?.let { arrayOfNulls(it) }

        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)

        dotsLayout!!.removeAllViews()
        if (dots != null) {
            for (i in 0 until dots.size) {
                dots[i] = TextView(this)
                dots[i]?.text = Html.fromHtml("&#8226;")
                dots[i]?.setTextSize(35F)
                dots[i]?.setTextColor(colorsInactive[currentPage])
                dotsLayout!!.addView(dots[i])
            }
        }

        if (dots != null) {
            if (dots.size > 0) dots[currentPage]?.setTextColor(colorsActive[currentPage])
        }
    }

    private fun launchHomeScreen() {
        prefManager!!.setFirstTimeLaunch(false)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    //	viewpager change listener
    var viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            addBottomDots(position)
            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts!!.size - 1) {
                // last page. make button text to GOT IT
                app_continue_button!!.text = "Get started"
                //  launchHomeScreen();
//                btnNext.setText(getString(R.string.start));
                btnSkip!!.visibility = View.GONE
            } else {
                // still pages are left
//                btnNext.setText("Next");
                btnSkip!!.visibility = View.GONE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}

        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    /**
     * Making notification bar transparent
     */
    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.TRANSPARENT)
        }
    }

    class MyViewPagerAdapter : PagerAdapter() {
        var layoutInflater: LayoutInflater? = null
        var mContext: Context? = null

        fun MyViewPagerAdapter() {

        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = mContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
            val view: View? = layoutInflater?.inflate(SliderActivity.layouts[position], container, false)
            container.addView(view)
            return view!!
        }

        override fun getCount(): Int {
            return layouts.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view == obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            val view = obj as View
            container.removeView(view)
        }
    }
}