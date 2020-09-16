package com.example.mvvmapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmapplication.R
import com.example.mvvmapplication.data.lokal.entity.Movie
import com.example.mvvmapplication.data.model.MovieResponse
import com.example.mvvmapplication.data.respository.Resource
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val vm: MovieViewModel by viewModel()
    private var listmovie : MutableList<MovieResponse.Result> = mutableListOf()
    private var groupAdapter: GroupAdapter<ViewHolder> = GroupAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initrv()

        vm.observeListMovie().observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    it.loadingData?.let { movies ->
                        if (listmovie.isNotEmpty()) listmovie.clear()
                        listmovie.addAll(movies.results)
                        groupAdapter.notifyDataSetChanged()

                        segaran.isRefreshing = false
                    }
                }
                is Resource.Success -> {
                    it.successData.let { movies ->
                        if (listmovie.isNotEmpty()) listmovie.clear()
                        movies?.results?.forEach {
                            groupAdapter.add(MovieItem(it))
                        }
                    }
                    segaran.isRefreshing = false
                }
                is Resource.Error -> {
                    Toast.makeText(
                        this,
                        "error pada->" + Timber.e(it.toString()),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        vm.getListMovie()

        segaran.setOnRefreshListener {
            vm.getListMovie()
        }
    }

    private fun initrv() {
        movie_rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = groupAdapter
        }
    }
}