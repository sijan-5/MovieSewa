package com.example.moviesewa.mvvm

import com.example.moviesewa.data_classes.MovieDetails
import com.example.moviesewa.data_classes.MoviesCollection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(val movieApi : MovieServiceApi)  : RepositoryInterface{
    override suspend fun fetchMovies() : kotlinx.coroutines.flow.Flow<ResponseResult<MoviesCollection>>{
       return checkApiResponse{
           movieApi.getMovieList("day")
       }
    }

    override suspend fun getMovieDetails(movieId :Int): Flow<ResponseResult<MovieDetails>> {
        return checkApiResponse {
            movieApi.getMovieDetails(movieId)
        }
    }

    override suspend fun searchMovie(
        query: String,
        api_key: String
    ): Flow<ResponseResult<MoviesCollection>> {

        return checkApiResponse {
            movieApi.searchMovie(query, api_key)
        }
    }
}

fun <T> checkApiResponse(block : suspend () -> T)  : Flow<ResponseResult<T>>
{
    return flow {
        emit(ResponseResult.Loading())

        try {
            emit(ResponseResult.Success(block()))
        }
        catch (e : Exception)
        {
            emit(ResponseResult.Failure(e.message.toString()))
        }
    }
}

sealed class ResponseResult<out T>
{
    class Success <T> (val successData : T): ResponseResult<T>()
    class Loading : ResponseResult<Nothing>()
    class Failure<F> ( val error : String) : ResponseResult<F>()
}