package com.example.mvvmapplication.data.respository

import android.widget.Toast
import com.example.mvvmapplication.data.lokal.dao.MovieDao
import com.example.mvvmapplication.data.lokal.entity.Movie
import com.example.mvvmapplication.data.model.MovieResponse
import com.example.mvvmapplication.data.remote.apiService
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class MovieRespository(private val apiService: apiService,
                       private val movieDao: MovieDao) {

    fun getListMovie(): Observable<Resource<List<Movie>>> {
        return object : PersistableNetworkResourceCall<MovieResponse.Movie, List<Movie>>(){
            override fun loadFromDatabase(): Maybe<List<Movie>> {
                return movieDao.findAllMovie()
            }

            override fun createNetworkCall(): Single<MovieResponse.Movie> {
                return apiService.getMyMovie()
            }

            override fun onNetworkCallSuccess(
                emitter: ObservableEmitter<Resource<List<Movie>>>,
                response: MovieResponse.Movie
            ) {
                if (response.results.isEmpty()){
                    emitter.onNext(Resource.Error("Data tidak ada", null))
                } else{
                    movieDao.deleteAll()

                    response.results.let {
                        Timber.d("masukan kedalam tabel")
                        val semuadata = it.map { data -> Movie.from(data) }
                        movieDao.insert(semuadata)
                    }
                    emitter.setDisposable(movieDao.streamAll()
                        .subscribeBy(
                            onNext = {
                                emitter.onNext(Resource.Success(it))
                            }
                        ))
                }
            }

        }.resourceObservable
    }
    }