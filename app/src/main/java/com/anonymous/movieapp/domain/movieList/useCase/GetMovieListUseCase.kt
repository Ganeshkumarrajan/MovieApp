package com.anonymous.movieapp.domain.movieList.useCase

import com.anonymous.movieapp.domain.utils.ResultState
import com.anonymous.movieapp.domain.movieList.model.MovieDomain
import com.anonymous.movieapp.domain.movieList.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMovieListUseCase(private val movieRepository: MovieRepository) {
    suspend fun operator(): Flow<ResultState<List<MovieDomain>>> =
        TODO("Not yet implemented")
}
