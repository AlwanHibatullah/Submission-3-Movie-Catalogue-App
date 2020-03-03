package com.alwandroid.moviecatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alwandroid.moviecatalogue.R
import com.alwandroid.moviecatalogue.api.Constants
import com.alwandroid.moviecatalogue.model.Movie
import com.alwandroid.moviecatalogue.model.TvShow
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TVSHOW = "extra_tvshow"
        const val EXTRA_SEPARATOR = "separator"
    }

    private lateinit var movie: Movie
    private lateinit var tvShow: TvShow
    private var titleBar: String? = null
    private var separator: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        separator = intent.getIntExtra(EXTRA_SEPARATOR, 0)

        if (separator == 1) {
            movie = intent.getParcelableExtra(EXTRA_MOVIE) as Movie
            loadMovieDetail()
            titleBar = movie.title
        } else {
            tvShow = intent.getParcelableExtra(EXTRA_TVSHOW) as TvShow
            loadTvShowDetail()
            titleBar = tvShow.name
        }

        supportActionBar?.apply {
            title = titleBar
            setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadMovieDetail() {
        Glide.with(this)
            .load("${Constants.backdrop_url}${movie.backdrop_path}")
            .into(img_backdrop)
        Glide.with(this)
            .load("${Constants.poster_url}${movie.poster_path}")
            .into(img_poster)
        tv_title.text = movie.title
        val date_array: List<String>? = movie.release_date?.split("-")
        tv_year.text = date_array?.first()
        tv_ratings.text = movie.vote_average.toString()
        tv_language.text = movie.original_language
        tv_vote.text = movie.vote_count.toString()
        tv_synopsis.text = movie.overview
    }

    private fun loadTvShowDetail() {
        Glide.with(this)
            .load("${Constants.backdrop_url}${tvShow.backdrop_path}")
            .into(img_backdrop)
        Glide.with(this)
            .load("${Constants.poster_url}${tvShow.poster_path}")
            .into(img_poster)
        tv_title.text = tvShow.name
        val date_array: List<String>? = tvShow.first_air_date?.split("-")
        tv_year.text = date_array?.first()
        tv_ratings.text = tvShow.vote_average.toString()
        tv_language.text = tvShow.original_language
        tv_vote.text = tvShow.vote_count.toString()
        tv_synopsis.text = tvShow.overview
    }

}
