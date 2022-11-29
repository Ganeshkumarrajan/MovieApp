package com.anonymous.movieapp.data.repository

import com.anonymous.movieapp.data.mapper.MovieToDomainMapper
import com.anonymous.movieapp.data.api.MovieService
import com.anonymous.movieapp.domain.movieDetails.useCase.MovieDetailsDomain
import com.anonymous.movieapp.domain.movieList.model.MovieDomain
import com.anonymous.movieapp.domain.movieList.repository.MovieRepository
import javax.inject.Inject

const val imageDomainName = "https://image.tmdb.org/t/p/w500"

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieService,
    private val movieMapper: MovieToDomainMapper
) : MovieRepository {
    override suspend fun getMovies(): List<MovieDomain> =
        movieMapper.mapMovie(api.movieList())

    override suspend fun getMovieDetails(id: Long): MovieDetailsDomain =
        movieMapper.mapMovieDetails(api.movieDetails(id))
}
