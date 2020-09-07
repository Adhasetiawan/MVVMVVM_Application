package com.example.mvvmapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmapplication.R
import com.example.mvvmapplication.data.lokal.entity.Movie
import com.example.mvvmapplication.data.respository.Resource
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val vm: MovieViewModel by viewModel()
    private var listmovie : MutableList<Movie> = mutableListOf()
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
                        listmovie.addAll(movies)
                        groupAdapter.notifyDataSetChanged()

                        segaran.isRefreshing = false
                    }
                }
                is Resource.Success -> {
                    it.successData.let { movies ->
                        movies?.forEach {
                            groupAdapter.add(MovieItem(it))
                        }
                    }
                    segaran.isRefreshing = false
                }
                is Resource.Error -> {
                    Timber.e(it.msg)
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