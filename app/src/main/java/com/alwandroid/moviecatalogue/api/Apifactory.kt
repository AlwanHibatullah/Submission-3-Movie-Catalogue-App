package com.alwandroid.moviecatalogue.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apifactory {
    private var retrofit: Retrofit? = null

    fun getUrl(): Retrofit? {
        val gson = GsonBuilder().setLenient().create()
        val log = HttpLoggingInterceptor()

        val client = OkHttpClient.Builder()

        client.addInterceptor(log)

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.baseurl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofit
    }
}