package com.example.moviesewa.mvvm.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesewa.data_classes.MovieDetails
import com.example.moviesewa.mvvm.RepositoryInterface
import com.example.moviesewa.mvvm.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: RepositoryInterface) : ViewModel() {
    private val _movieDetail = MutableStateFlow<ResponseResult<MovieDetails>>(ResponseResult.Loading)
    val movieDetail = _movieDetail.asStateFlow()

    fun getMovieDetail(id : Int)
    {
        viewModelScope.launch {
            repository.getMovieDetails(id).collect{result ->
                _movieDetail.value = result
            }
        }
    }
}