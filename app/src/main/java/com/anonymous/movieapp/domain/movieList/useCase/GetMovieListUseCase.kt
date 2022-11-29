package com.anonymous.movieapp.domain.movieList.useCase

import com.anonymous.movieapp.domain.movieList.model.MovieDomain
import com.anonymous.movieapp.domain.movieList.repository.MovieRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(): List<MovieDomain> =
        movieRepository.getMovies()
}
