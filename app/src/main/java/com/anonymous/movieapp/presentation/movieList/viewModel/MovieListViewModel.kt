package com.anonymous.movieapp.presentation.movieList.viewModel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.movieapp.domain.movieList.model.MovieDomain
import com.anonymous.movieapp.domain.movieList.useCase.GetMovieListUseCase
import com.anonymous.movieapp.presentation.utils.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val getMoviesUseCase: GetMovieListUseCase) :
    ViewModel() {
    @VisibleForTesting
    internal val stateFlow = MutableStateFlow<ScreenState<List<MovieDomain>>>(ScreenState.Loading())
    val state: StateFlow<ScreenState<List<MovieDomain>>> = stateFlow

    fun getMovies() {
        viewModelScope.launch {
            kotlin.runCatching {
                getMoviesUseCase()
            }.onSuccess {
                stateFlow.tryEmit(ScreenState.Success(it))
            }.onFailure {
                stateFlow.tryEmit(ScreenState.Error())
            }
        }
    }
}
