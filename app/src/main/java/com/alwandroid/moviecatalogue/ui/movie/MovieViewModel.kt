package com.alwandroid.moviecatalogue.ui.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alwandroid.moviecatalogue.api.Apifactory
import com.alwandroid.moviecatalogue.model.EndpointMovie
import com.alwandroid.moviecatalogue.model.Movie
import com.alwandroid.moviecatalogue.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MovieViewModel : ViewModel(){

    private val listMovies : MutableLiveData<ArrayList<Movie>> = MutableLiveData()

    fun setListMovie(){
        val ep: EndpointMovie = Apifactory.getUrl()!!.create(EndpointMovie::class.java)
        val apiToken = com.alwandroid.moviecatalogue.BuildConfig.TMDB_API_KEY
        val call: Call<MovieResponse> = ep.getMovie(apiToken, EndpointMovie.lang)
        call.enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("Error: ", t.message)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                try {
                    val list = response.body()?.results
                    if (response.isSuccessful){
                        listMovies.value = list
                    }
                } catch (e:Exception){
                    Log.d("Error : ", e.message)
                }
            }
        })
    }

    fun getListMovie(): LiveData<ArrayList<Movie>>{
        return listMovies
    }

}