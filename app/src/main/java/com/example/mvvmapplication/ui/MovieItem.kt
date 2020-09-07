package com.example.mvvmapplication.ui

import com.example.mvvmapplication.R
import com.example.mvvmapplication.data.lokal.entity.Movie
import com.example.mvvmapplication.util.scheduler.loadImageFromUrl
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_row_movie.view.*

class MovieItem(val mymovie : Movie) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            itemView.txt_movie_title.text = mymovie.movie_title
            itemView.poster_img.loadImageFromUrl(mymovie.movie_poster)
        }
    }

    override fun getLayout(): Int = R.layout.item_row_movie
}