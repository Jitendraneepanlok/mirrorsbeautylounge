package com.beauty.mirrors.Activity

import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.beauty.mirrors.R

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
        initView()

    }

    private fun initView() {
        var img_back: AppCompatImageView = findViewById(R.id.img_back)
        img_back.setOnClickListener(object : View.OnClickListener {
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

        /*val noBtn = dialog.findViewById(R.id.noBtn) as TextView
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }*/
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.show()


    }
}
