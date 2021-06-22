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
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.beauty.mirrors.R
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.custom_login_layout.*



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
        val maxTimeInMilliseconds: Long = 30000
        startTimer(maxTimeInMilliseconds, 1000)
        initView()

    }

    private fun startTimer(maxTimeInMilliseconds: Long, i: Int) {
        val count: CountDownTimer

        count = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val remainedSecs = millisUntilFinished / 1000
                txt_time.setText("" + remainedSecs / 60 + ":" + remainedSecs % 60)
            }

            override fun onFinish() {
                txt_time.setText("00:00")
                txt_resend.visibility = View.VISIBLE

                cancel()
            }
        }.start()
    }


    private fun initView() {
        txt_pin_entry.setHint("12345")

        img_back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
        })

        txt_edit_mobile.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
        })

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

        val txt_redirecting : AppCompatTextView = dialog.findViewById(R.id.txt_redirecting)
        txt_redirecting.setOnClickListener {
            startActivity(Intent(applicationContext,HomeActivity::class.java))
            dialog.dismiss()
        }
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.show()

    }
}
