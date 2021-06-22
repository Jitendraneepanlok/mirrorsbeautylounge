package com.beauty.mirrors.Activity

import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.alimuzaffar.lib.pin.PinEntryEditText
import com.beauty.mirrors.R
import java.time.Clock.tick


class OtpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.pink));
        }
        val maxTimeInMilliseconds: Long = 3000
        startTimer(maxTimeInMilliseconds, 1000)
        initView()

    }

    private fun startTimer(maxTimeInMilliseconds: Long, i: Int) {
        var txt_time: AppCompatTextView = findViewById(R.id.txt_time)
        val count: CountDownTimer

        count = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val remainedSecs = millisUntilFinished / 1000
                txt_time.setText("" + remainedSecs / 60 + ":" + remainedSecs % 60)
            }

            override fun onFinish() {
                txt_time.setText("00:00")
                cancel()
            }
        }.start()
    }


    private fun initView() {
        var txt_pin_entry: PinEntryEditText = findViewById(R.id.txt_pin_entry)
        txt_pin_entry.setHint("12345")

        var img_back: AppCompatImageView = findViewById(R.id.img_back)
        img_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
        })

        var txt_edit_mobile: AppCompatTextView = findViewById(R.id.txt_edit_mobile)
        txt_edit_mobile.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
        })

        var btn_login: AppCompatButton = findViewById(R.id.btn_login)
        btn_login.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                OpenDialog()
            }

        })
    }

    private fun OpenDialog() {
        val dialog = Dialog(this, R.style.DialogTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_login_layout)
        dialog.setCancelable(true)

        val txt_redirecting :AppCompatTextView = dialog.findViewById(R.id.txt_redirecting)
        txt_redirecting.setOnClickListener {
            startActivity(Intent(applicationContext,HomeActivity::class.java))
            dialog.dismiss()
        }
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.show()

    }
}
