package com.anonymous.movieapp.domain.utils

sealed class ResultState<T> {
    data class Success<T>(val data: T) : ResultState<T>()
}
