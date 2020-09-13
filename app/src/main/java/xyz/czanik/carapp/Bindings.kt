package xyz.czanik.carapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:src")
fun loadImageFromUrl(imageView: ImageView, url: String) = Picasso.get().load(url).into(imageView)