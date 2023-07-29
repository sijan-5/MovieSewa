package com.example.moviesewa.mvvm

import android.util.Log
import com.example.moviesewa.data_classes.MovieDetails
import com.example.moviesewa.data_classes.MoviesCollection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
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
        query: String
    ): Flow<ResponseResult<MoviesCollection>> {

        return checkApiResponse {
            movieApi.searchMovie(query)
        }
    }
}

fun <T> checkApiResponse(block : suspend () -> T)  : Flow<ResponseResult<T>>
{
    return flow {
        emit(ResponseResult.Loading)

        try {
            emit(ResponseResult.Success(block()))
            throw Exception("error occured")
        }
        catch (e:Exception)
        {
            Log.d(" hi error", e.message.toString())
        }
    }.catch {
        Log.d("catch error", it.message.toString())
        emit(ResponseResult.Failure(it.message))
    }
}

sealed class ResponseResult<out T>
{
    class Success <T> (val successData : T): ResponseResult<T>()
    object Loading : ResponseResult<Nothing>()
    class Failure<F> ( val error : String?) : ResponseResult<F>()
}