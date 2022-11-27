package com.anonymous.movieapp.data.mapper

import com.anonymous.movieapp.data.model.Movie
import com.anonymous.movieapp.data.model.MovieDetails
import com.anonymous.movieapp.data.repository.imageDomainName
import com.anonymous.movieapp.domain.movieDetails.useCase.MovieDetailsDomain
import com.anonymous.movieapp.domain.movieList.model.MovieDomain

class MovieToDomainMapper() {
    fun mapMovie(movie: Movie): List<MovieDomain> =
        movie.results?.map {
            MovieDomain(
                it.id ?: 0,
                it.title,
                concertToCompleteImagePath(it.posterPath ?: ""),
                convertToYear(it.releaseDate ?: "")
            )
        } ?: kotlin.run {
            emptyList()
        }

    fun mapMovieDetails(movieDetails: MovieDetails): MovieDetailsDomain =
        MovieDetailsDomain(
            title = movieDetails.title,
            description = movieDetails.overview,
            imageURl = "$imageDomainName$movieDetails.posterPath",
            yearOfRelease = convertToYear(movieDetails.releaseDate)
        )


    private fun convertToYear(releaseDate: String): String =
        releaseDate.split("-")[0]

    private fun concertToCompleteImagePath(imageNme: String) =
        "$imageDomainName$imageNme"
}
