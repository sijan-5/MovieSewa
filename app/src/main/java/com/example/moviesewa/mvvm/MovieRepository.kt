package com.example.moviesewa.mvvm

import com.example.moviesewa.data_classes.TrendingMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(val movieApi : MovieServiceApi)  : RepositoryInterface{
    override suspend fun fetchMovies() : kotlinx.coroutines.flow.Flow<ResponseResult<TrendingMovies>>{
       return checkApiResponse{
           movieApi.getMovieList("day")
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