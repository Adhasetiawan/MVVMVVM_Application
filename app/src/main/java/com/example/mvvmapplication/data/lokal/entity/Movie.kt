package com.example.mvvmapplication.data.lokal.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvmapplication.data.model.MovieResponse
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class Movie (
    @PrimaryKey(autoGenerate = false) val movie_id: Int,
    @ColumnInfo(name = "title") val movie_title: String,
    @ColumnInfo(name = "poster") val movie_poster: String

): Parcelable {
    companion object {
        fun from(data : MovieResponse.Result) = Movie (
            data.id,
            data.title,
            data.poster_path)
    }
}