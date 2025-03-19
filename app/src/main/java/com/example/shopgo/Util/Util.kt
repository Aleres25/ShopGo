package com.example.shopgo.Util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.shopgo.R

fun ImageView.getImageFromUrl(url: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(progressDrawable).error(R.mipmap.ic_launcher_round)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}

fun placeHolderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }

}