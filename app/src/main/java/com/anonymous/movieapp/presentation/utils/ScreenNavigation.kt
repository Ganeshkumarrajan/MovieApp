package com.anonymous.movieapp.presentation.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anonymous.movieapp.presentation.movieDetails.screen.MovieDetailsScreen
import com.anonymous.movieapp.presentation.movieList.screen.MovieListScreen

@Composable
fun NavigationApp(navController: NavHostController){
    NavHost(navController =navController ,
        startDestination = ScreenNames.List.route){
        composable(route = ScreenNames.List.route){
            MovieListScreen(navController)
        }
        composable(route = "${ScreenNames.Details.route}/{movieId}"){ backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?:"0"
            MovieDetailsScreen(movieID =  movieId.toLong())
        }
    }

}

sealed class ScreenNames(val route: String){
    object List:ScreenNames ("list")
    object Details:ScreenNames("details")
}
