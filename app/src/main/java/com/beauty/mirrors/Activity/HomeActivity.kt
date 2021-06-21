package com.beauty.mirrors.Activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.beauty.mirrors.R
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import com.synnapps.carouselview.ViewListener


class HomeActivity : AppCompatActivity() {
    var sampleImages = intArrayOf(
        R.drawable.banner_images,
        R.drawable.dubai,
        R.drawable.banner_images,
        R.drawable.dubai,
        R.drawable.banner_images
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.pink));
        }

        var carouselView: CarouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.size);

        carouselView.setImageListener(imageListener);
    }

    val imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(sampleImages[position]);

    }

}