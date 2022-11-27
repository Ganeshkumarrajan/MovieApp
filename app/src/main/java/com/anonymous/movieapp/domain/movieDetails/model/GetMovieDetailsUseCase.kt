package com.anonymous.movieapp.domain.movieDetails.model

import com.anonymous.movieapp.domain.movieDetails.useCase.MovieDetailsDomain
import com.anonymous.movieapp.domain.movieList.repository.MovieRepository

class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {
    suspend fun operator(id: Long): MovieDetailsDomain = movieRepository.getMovieDetails(id)
}
