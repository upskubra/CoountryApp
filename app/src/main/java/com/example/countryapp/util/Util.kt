package com.example.countryapp.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.countryapp.R

//  create Extension

fun ImageView.downloadFromUrl(Url: String?, circularProgressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(circularProgressDrawable)
        .error(R.drawable.error_image)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(Url)
        .into(this)


}

    fun placeholderProgressBar(context: Context): CircularProgressDrawable {

        // when loading images
        return CircularProgressDrawable(context)
            .apply {
                strokeWidth = 5f
                centerRadius = 40f
                start()
            }
    }

@BindingAdapter("android:DownloadUrl")
fun downloadImage(view : ImageView, url : String?){
    view.downloadFromUrl(url, placeholderProgressBar(view.context))
}