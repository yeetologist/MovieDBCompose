package com.github.yeetologist.moviedbcompose.data

import com.github.yeetologist.moviedbcompose.model.Movie
import com.github.yeetologist.moviedbcompose.model.MoviesData

class MovieRepository {

    fun getMovies(): List<Movie> {
        return MoviesData.movies
    }

    fun searchMovies(query: String): List<Movie> {
        return MoviesData.movies.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getMoviesById(id: String): Movie? {
        return MoviesData.movies.firstOrNull {
            it.id == id
        }
    }
}