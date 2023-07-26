package com.example.moviesewa.mvvm

import com.example.moviesewa.data_classes.TrendingMovies
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface RepositoryInterface  {
    suspend fun  fetchMovies() : Flow<ResponseResult<TrendingMovies>>
}


