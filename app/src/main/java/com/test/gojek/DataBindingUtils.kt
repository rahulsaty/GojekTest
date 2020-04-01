package com.test.gojek

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context).load(imageUrl).transition(
        DrawableTransitionOptions.withCrossFade()
    ).apply(RequestOptions.circleCropTransform()).into(view)
}












