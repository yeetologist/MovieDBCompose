package com.github.yeetologist.moviedbcompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object MovieDetail : Screen("home/{movieId}") {
        fun createRoute(movieId: String) = "home/$movieId"
    }
}
