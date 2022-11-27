package com.anonymous.movieapp.domain.movieList.repository

import com.anonymous.movieapp.domain.movieDetails.useCase.MovieDetailsDomain
import com.anonymous.movieapp.domain.movieList.model.MovieDomain

interface MovieRepository {
    suspend fun getMovies(): List<MovieDomain>
    suspend fun getMovieDetails(id: Long): MovieDetailsDomain
}
