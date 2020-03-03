package com.alwandroid.moviecatalogue.ui.movie

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.alwandroid.moviecatalogue.R
import com.alwandroid.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progressBar)

        progressBar.visibility = View.VISIBLE

        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv_movies.layoutManager = GridLayoutManager(view.context, 2)
        } else {
            rv_movies.layoutManager = GridLayoutManager(view.context, 4)
        }

        rv_movies.setHasFixedSize(true)
        val movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieViewModel.setListMovie()
        movieViewModel.getListMovie().observe(this, Observer<ArrayList<Movie>> { t: ArrayList<Movie> ->
            progressBar.visibility = View.GONE
            movieAdapter = MovieAdapter(view.context, t)
            rv_movies.adapter = movieAdapter
            movieAdapter.model = t
        })
    }
}
