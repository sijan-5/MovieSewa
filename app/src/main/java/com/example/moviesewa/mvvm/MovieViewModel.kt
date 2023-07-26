package com.example.moviesewa.mvvm

import androidx.lifecycle.ViewModel
import com.example.moviesewa.data_classes.TrendingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: RepositoryInterface) : ViewModel() {

    suspend fun getMovies() : Response<TrendingMovies>
    {
         return repository.fetchMovies()
    }
}