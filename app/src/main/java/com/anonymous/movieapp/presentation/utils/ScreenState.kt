package com.anonymous.movieapp.presentation.utils

sealed class ScreenState<T> {
    data class Success<T>(val data: T) : ScreenState<T>()
    class Loading<T> : ScreenState<T>()
    class Error<T> : ScreenState<T>()
}
