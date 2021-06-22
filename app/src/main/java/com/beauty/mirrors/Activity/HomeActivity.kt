package com.beauty.mirrors.Activity

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.beauty.mirrors.R
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_home.*


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

        carouselView.setPageCount(sampleImages.size);

        carouselView.setImageListener(imageListener);
    }

    val imageListener = ImageListener { position, imageView ->
        imageView.setImageResource(sampleImages[position]);

    }

}