package com.anonymous.movieapp.domain.movieDetails.model

import com.anonymous.movieapp.domain.movieDetails.useCase.MovieDetailsDomain
import com.anonymous.movieapp.domain.movieList.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend  operator fun invoke(id: Long): MovieDetailsDomain = movieRepository.getMovieDetails(id)
}
