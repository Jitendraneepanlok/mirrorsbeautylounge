package com.beauty.mirrors.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.beauty.mirrors.R

class SplashActivity : AppCompatActivity() {
    // Splash screen timer
    private val SPLASH_TIME_OUT = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler(Looper.getMainLooper()).postDelayed({
            // Your Code
            startActivity(Intent(this, SliderActivity::class.java))
            // close this activity
            finish()
        }, 3000)

    }
}