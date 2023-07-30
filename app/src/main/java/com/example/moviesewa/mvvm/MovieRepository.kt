package com.example.moviesewa.mvvm

import android.util.Log
import com.example.moviesewa.data_classes.LatestTVID
import com.example.moviesewa.data_classes.MovieDetails
import com.example.moviesewa.data_classes.MoviesCollection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(private val remoteMovieApi : MovieServiceApi)  : RepositoryInterface{
    override suspend fun fetchMovies(time_window: String): kotlinx.coroutines.flow.Flow<ResponseResult<MoviesCollection>>{
       return checkApiResponse{
           remoteMovieApi.getMovieList(time_window)
       }
    }

    override suspend fun getMovieDetails(movieId :Int): Flow<ResponseResult<MovieDetails>> {
        return checkApiResponse {
            remoteMovieApi.getMovieDetails(movieId)
        }
    }

    override suspend fun searchMovie(
        query: String
    ): Flow<ResponseResult<MoviesCollection>> {

        return checkApiResponse {
            remoteMovieApi.searchMovie(query)
        }
    }

    override suspend fun latestTvId(): Flow<ResponseResult<LatestTVID>> {

       return  checkApiResponse {
            remoteMovieApi.latestTV()
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
            emit(ResponseResult.Failure(e.message))
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