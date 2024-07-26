package com.example.test

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun ImageView.loadImageUrl(url: String?) {
    Glide.with(this).load(url).into(this)
}