package com.beauty.mirrors.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.beauty.mirrors.R
import com.hbb20.CountryCodePicker

class LoginActivity : AppCompatActivity()/*, CountryCodePicker.OnCountryChangeListener*/ {
    private var countryCode: String? = null
    private var countryName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.pink));
        }
        initView()

    }

    private fun initView() {
        var btnforotp:AppCompatButton = findViewById(R.id.btn_otp)

        btnforotp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, OtpActivity::class.java)
                startActivity(intent)
            }

        })
        var ccp: CountryCodePicker? = findViewById(R.id.country_code_picker)
        ccp?.setCcpClickable(false)

//        ccp!!.setOnCountryChangeListener(this)
        //to set default country code as Japan
//        ccp!!.setDefaultCountryUsingNameCode("JP")
        }

   /* override fun onCountrySelected() {
        countryCode = ccp!!.selectedCountryCode
        countryName = ccp!!.selectedCountryName

        Log.e("", "Country Code" + countryCode)
        Log.e("", "Country Name " + countryName)
    }*/
}
