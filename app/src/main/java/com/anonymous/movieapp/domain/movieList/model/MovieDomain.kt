package com.anonymous.movieapp.domain.movieList.model

data class MovieDomain(
    val id: Long, val title: String?, val imageURL: String?,
    val releasedYear: String?
)