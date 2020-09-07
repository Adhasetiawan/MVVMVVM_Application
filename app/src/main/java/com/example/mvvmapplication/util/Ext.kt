package com.example.mvvmapplication.util.scheduler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmapplication.R

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invis(){
    this.visibility = View.INVISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun TextView.formatRupiah(angka : Double){
    this.text = String.format("Rp. %,.0f", angka).replace(",".toRegex(), ".")
}

fun ImageView.loadImageFromUrl(url: String?){
    Glide.with(this.context)
        .load(url)
        .apply(RequestOptions.circleCropTransform().error(R.drawable.movie_logo))
        .into(this)
}

fun ImageView.loadImageFromUrlRect(url: String){
    Glide.with(this.context)
        .load(url)
        .into(this)
}

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}