package com.anonymous.movieapp.data.api

import com.anonymous.movieapp.data.model.Movie
import com.anonymous.movieapp.data.model.MovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("3/discover/movie")
    suspend fun movieList(
        @Query("api_key") apiKey: String = "c9856d0cb57c3f14bf75bdc6c063b8f3",
        @Query("language") language: String = "en-US",
    ): Movie

    @GET("3/{id}")
    suspend fun movieDetails(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    ): MovieDetails

}
