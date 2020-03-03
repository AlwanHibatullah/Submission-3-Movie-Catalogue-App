package com.alwandroid.moviecatalogue.ui.tvshow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alwandroid.moviecatalogue.api.Apifactory
import com.alwandroid.moviecatalogue.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowViewModel : ViewModel(){

    private val listTvShow: MutableLiveData<ArrayList<TvShow>> = MutableLiveData()

    fun setListTVShow(){
        val ep: EndpointTvShow = Apifactory.getUrl()!!.create(EndpointTvShow::class.java)
        val apiToken = com.alwandroid.moviecatalogue.BuildConfig.TMDB_API_KEY
        val call: Call<TvShowResponse> = ep.getTvShow(apiToken, EndpointMovie.lang)
        call.enqueue(object : Callback<TvShowResponse>{
            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.d("Error: ", t.message)
            }

            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                try {
                    val list = response.body()?.results
                    if (response.isSuccessful){
                        listTvShow.value = list
                    }
                } catch (e:Exception){
                    Log.d("Error : ", e.message)
                }
            }
        })
    }

    fun getListTvShow(): LiveData<ArrayList<TvShow>>{
        return listTvShow
    }
}