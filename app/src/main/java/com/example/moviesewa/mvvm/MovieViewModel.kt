package com.example.moviesewa.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesewa.data_classes.MovieData
import com.example.moviesewa.data_classes.TrendingMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: RepositoryInterface) : ViewModel() {

    suspend fun getMovies() : Flow<ResponseResult<TrendingMovies>>
    {
        return  repository.fetchMovies()
    }
}