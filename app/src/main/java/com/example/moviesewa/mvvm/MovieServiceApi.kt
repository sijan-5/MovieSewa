package com.example.moviesewa.mvvm

import com.example.moviesewa.data_classes.LatestTVID
import com.example.moviesewa.data_classes.MovieDetails
import com.example.moviesewa.data_classes.MoviesCollection
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieServiceApi{


    @GET("trending/movie/{time_window}")
    suspend fun getMovieList(@Path("time_window") time :String) : MoviesCollection


    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movie_id: Int) : MovieDetails



    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query :String, @Query("api_key") api_key : String ="78cec9d5b33781dd70509ca0e6e88019"):MoviesCollection


    @GET("tv/latest")
    suspend fun latestTV() : LatestTVID
}

