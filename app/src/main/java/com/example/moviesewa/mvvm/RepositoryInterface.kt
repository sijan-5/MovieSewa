package com.example.moviesewa.mvvm

import com.example.moviesewa.data_classes.LatestTVID
import com.example.moviesewa.data_classes.MovieDetails
import com.example.moviesewa.data_classes.MoviesCollection
import kotlinx.coroutines.flow.Flow


interface RepositoryInterface  {
    suspend fun  fetchMovies(time_window :String) : Flow<ResponseResult<MoviesCollection>>
    suspend fun getMovieDetails(movieId :Int) : Flow<ResponseResult<MovieDetails>>
    suspend fun searchMovie(query:String) : Flow<ResponseResult<MoviesCollection>>
    suspend fun latestTvId() : Flow<ResponseResult<LatestTVID>>
}


