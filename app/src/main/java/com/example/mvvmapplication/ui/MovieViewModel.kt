package com.example.mvvmapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmapplication.data.lokal.entity.Movie
import com.example.mvvmapplication.data.model.MovieResponse
import com.example.mvvmapplication.data.respository.MovieRespository
import com.example.mvvmapplication.data.respository.Resource
import com.example.mvvmapplication.util.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class MovieViewModel(private val movieRespository: MovieRespository,
                     var schedulerProvider: SchedulerProvider): ViewModel() {

    private val disposables = CompositeDisposable()

    private val listmovie = MutableLiveData<Resource<MovieResponse.Movie>>()

    fun observeListMovie() = listmovie

    fun getListMovie(){
        movieRespository.getListMovie()
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe({
                listmovie.postValue(Resource.Success(it))
            }, {

            }).addTo(disposables)
    }
}