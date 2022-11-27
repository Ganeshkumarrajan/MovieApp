package com.anonymous.movieapp.domain.utils

sealed class ErrorEntity{
    object Network: ErrorEntity()
}