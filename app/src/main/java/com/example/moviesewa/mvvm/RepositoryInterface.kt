package com.example.moviesewa.mvvm

import com.example.moviesewa.data_classes.TrendingMovies
import retrofit2.Response


interface RepositoryInterface  {
    suspend fun fetchMovies() : Response<TrendingMovies>
}


