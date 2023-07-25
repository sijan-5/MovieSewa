package com.example.moviesewa.mvvm

import com.example.moviesewa.data_classes.TrendingMovies
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(val movieApi : MovieServiceApi)  : RepositoryInterface{
    override  suspend fun fetchMovies() : Response<TrendingMovies>
    {
        return movieApi.getMovieList("day")
    }

}