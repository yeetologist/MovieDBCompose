package com.github.yeetologist.moviedbcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.yeetologist.moviedbcompose.ui.navigation.Screen
import com.github.yeetologist.moviedbcompose.ui.screen.about.AboutScreen
import com.github.yeetologist.moviedbcompose.ui.screen.detail.MovieDetailScreen
import com.github.yeetologist.moviedbcompose.ui.screen.home.HomeScreen

@Composable
fun MovieDBApp(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToAbout = { navController.navigate(Screen.About.route) },
                navigateToDetail = {
                    navController.navigate(Screen.MovieDetail.createRoute(it))
                }
            )
        }
        composable(route = Screen.About.route) {
            AboutScreen(navigateBack = {
                navController.navigateUp()
            })
        }
        composable(
            route = Screen.MovieDetail.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.StringType
                }
            )
        ) {
            val movieId = it.arguments?.getString("movieId")
            if (movieId != null) {
                MovieDetailScreen(
                    movieId = movieId,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}