package com.example.moviesewa.mvvm.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesewa.data_classes.MoviesCollection
import com.example.moviesewa.mvvm.RepositoryInterface
import com.example.moviesewa.mvvm.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    private val _moviesStateFlow  = MutableStateFlow<ResponseResult<MoviesCollection>>(
        ResponseResult.Loading
    )
    val movieStateFlow = _moviesStateFlow.asStateFlow()
    init {
        getMovies(TimeWindow.Day)
    }
     fun getMovies(timeWindow: TimeWindow) {
         viewModelScope.launch {
                 repository.fetchMovies(timeWindow.time).collect{
                     _moviesStateFlow.value = it
                 }
         }
     }

    enum class TimeWindow(val time:String)
    {
        Day("day"),
        Week("week")
    }
}