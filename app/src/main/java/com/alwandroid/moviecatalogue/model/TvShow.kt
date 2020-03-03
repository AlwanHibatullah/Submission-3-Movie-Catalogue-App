package com.alwandroid.moviecatalogue.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

@Parcelize
data class TvShow(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("first_air_date")
    var first_air_date: String?,
    @SerializedName("vote_average")
    var vote_average: Double?,
    @SerializedName("original_language")
    var original_language: String?,
    @SerializedName("vote_count")
    var vote_count: Int?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("poster_path")
    var poster_path: String?,
    @SerializedName("backdrop_path")
    var backdrop_path: String?
) : Parcelable

@Parcelize
data class TvShowResponse(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var results: ArrayList<TvShow>,
    @SerializedName("totalResults")
    var totalResults: Int,
    @SerializedName("totalPages")
    var totalPages: Int
) : Parcelable

interface EndpointTvShow {
    @GET("discover/tv")
    fun getTvShow(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<TvShowResponse>
}