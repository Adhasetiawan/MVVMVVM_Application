package com.example.mvvmapplication.data.lokal.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.mvvmapplication.data.lokal.entity.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface MovieDao : BaseDao<Movie> {
    @Query("SELECT * FROM `movie` ORDER by movie_id DESC")
    fun findAllMovie() : Maybe<List<Movie>>

    @Query("SELECT * FROM `movie` ORDER by movie_id DESC")
    fun streamAll() : Flowable<List<Movie>>

    @Query("DELETE FROM `movie`")
    fun deleteAll()
}