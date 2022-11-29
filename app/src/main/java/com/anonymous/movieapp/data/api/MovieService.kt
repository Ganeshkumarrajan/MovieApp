package com.anonymous.movieapp.data.api

import com.anonymous.movieapp.BuildConfig
import com.anonymous.movieapp.data.model.Movie
import com.anonymous.movieapp.data.model.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("3/discover/movie")
    suspend fun movieList(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US",
    ): Movie

    @GET("3/movie/{id}")
    suspend fun movieDetails(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): MovieDetails

}
