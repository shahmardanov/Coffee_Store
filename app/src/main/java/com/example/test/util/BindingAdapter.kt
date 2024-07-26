package com.example.test.util

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.test.loadImageUrl

@BindingAdapter("load_image_local")
fun setImageSrc(imageView: ImageView, resId: Int) {
    imageView.setImageResource(resId)
}

@BindingAdapter("load_image_url")
fun setImageUrl(imageView: ImageView, url: String?) {
    imageView.loadImageUrl(url)
}