package com.example.moviesewa.mvvm

import com.example.moviesewa.data_classes.MovieDetails
import com.example.moviesewa.data_classes.MoviesCollection
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieServiceApi{

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3OGNlYzlkNWIzMzc4MWRkNzA1MDljYTBlNmU4ODAxOSIsInN1YiI6IjY0YmU5MjZmYjg2NWViMDBmZjRkYzczZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jnE3a8uulaWHYV41pYdcFFvalkKTJbaBKYBajz-a1XU",
        "accept: application/json"
    )
    @GET("trending/movie/{time_window}")
    suspend fun getMovieList(@Path("time_window") time :String) : MoviesCollection

    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3OGNlYzlkNWIzMzc4MWRkNzA1MDljYTBlNmU4ODAxOSIsInN1YiI6IjY0YmU5MjZmYjg2NWViMDBmZjRkYzczZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jnE3a8uulaWHYV41pYdcFFvalkKTJbaBKYBajz-a1XU",
        "accept: application/json"
    )
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movie_id: Int) : MovieDetails


    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3OGNlYzlkNWIzMzc4MWRkNzA1MDljYTBlNmU4ODAxOSIsInN1YiI6IjY0YmU5MjZmYjg2NWViMDBmZjRkYzczZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jnE3a8uulaWHYV41pYdcFFvalkKTJbaBKYBajz-a1XU",
        "accept: application/json"
    )
    @GET("search/movie")
    suspend fun searchMovie(@Query("query") query :String, @Query("api_key") api_key : String ="78cec9d5b33781dd70509ca0e6e88019"):MoviesCollection
}

