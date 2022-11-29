package com.anonymous.movieapp.presentation.movieDetails.viewModel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.movieapp.domain.movieDetails.model.GetMovieDetailsUseCase
import com.anonymous.movieapp.domain.movieDetails.useCase.MovieDetailsDomain
import com.anonymous.movieapp.presentation.utils.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) :
    ViewModel() {

    @VisibleForTesting
    private val _movieDetails =
        MutableStateFlow<ScreenState<MovieDetailsDomain>>(ScreenState.Loading())
    val movieDetails: StateFlow<ScreenState<MovieDetailsDomain>> = _movieDetails

    fun getMovieDetails(id: Long) {
        viewModelScope.launch {
            kotlin.runCatching {
                getMovieDetailsUseCase(id)
            }.onSuccess {
                _movieDetails.tryEmit(ScreenState.Success(it))
            }.onFailure {
                _movieDetails.tryEmit(ScreenState.Error())
            }
        }
    }
}
