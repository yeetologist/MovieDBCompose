package com.github.yeetologist.moviedbcompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.yeetologist.moviedbcompose.data.MovieRepository
import com.github.yeetologist.moviedbcompose.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreenViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _groupedMovies = MutableStateFlow(
        repository.getMovies()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )
    val groupedMovies: StateFlow<Map<Char, List<Movie>>> get() = _groupedMovies

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedMovies.value = repository.searchMovies(_query.value)
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    }
}

class HomeScreenViewModelFactory(private val repository: MovieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
