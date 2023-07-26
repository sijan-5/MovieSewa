package com.example.moviesewa.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesewa.data_classes.MovieData
import com.example.moviesewa.data_classes.TrendingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: RepositoryInterface) : ViewModel() {


    val movieList = MutableLiveData<List<MovieData>>()
    suspend fun getMovies()
    {
        val movie = mutableListOf<MovieData>()
         val response = repository.fetchMovies()

        if (response.isSuccessful)
        {
            response.body()!!.results.map { result ->
                movie.add(MovieData(result.poster_path, result.title, result.release_date))
            }
        }
        movieList.postValue(movie)

    }
}