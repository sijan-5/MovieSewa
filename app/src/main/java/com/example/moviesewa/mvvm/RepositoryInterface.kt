package com.example.moviesewa.mvvm

import com.example.moviesewa.data_classes.MovieData
import com.example.moviesewa.data_classes.MovieDetails
import com.example.moviesewa.data_classes.TrendingMovies
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface RepositoryInterface  {
    suspend fun  fetchMovies() : Flow<ResponseResult<TrendingMovies>>
    suspend fun getMovieDetails(movieId :Int) : Flow<ResponseResult<MovieDetails>>
}


