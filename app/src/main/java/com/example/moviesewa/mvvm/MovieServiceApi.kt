package com.example.moviesewa.mvvm

import com.example.moviesewa.data_classes.TrendingMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface MovieServiceApi{


    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3OGNlYzlkNWIzMzc4MWRkNzA1MDljYTBlNmU4ODAxOSIsInN1YiI6IjY0YmU5MjZmYjg2NWViMDBmZjRkYzczZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jnE3a8uulaWHYV41pYdcFFvalkKTJbaBKYBajz-a1XU",
        "accept: application/json"
    )
    @GET("trending/movie/{time_window}")
    suspend fun getMovieList(@Path("time_window") time :String) : Response<TrendingMovies>
}

