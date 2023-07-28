package com.example.moviesewa.mvvm

import androidx.lifecycle.ViewModel
import com.example.moviesewa.data_classes.MovieDetails
import com.example.moviesewa.data_classes.MoviesCollection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: RepositoryInterface) : ViewModel() {
    suspend fun getMovies() : Flow<ResponseResult<MoviesCollection>> = repository.fetchMovies()
    suspend fun getMovieDetails(movieId : Int) : Flow<ResponseResult<MovieDetails>> = repository.getMovieDetails(movieId)
    suspend fun searchMovie(query :String) : Flow<ResponseResult<MoviesCollection>> = repository.searchMovie(query)
}