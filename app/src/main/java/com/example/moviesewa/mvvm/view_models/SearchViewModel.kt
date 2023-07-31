package com.example.moviesewa.mvvm.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesewa.data_classes.MoviesCollection
import com.example.moviesewa.mvvm.RepositoryInterface
import com.example.moviesewa.mvvm.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(val repository : RepositoryInterface) : ViewModel() {
    private val _searchState = MutableStateFlow<ResponseResult<MoviesCollection>>(ResponseResult.Loading)
    val searchState = _searchState.asStateFlow()

    fun searchMovieCollection(query :String)
    {
        viewModelScope.launch {
            repository.searchMovie(query).collect{result ->
                _searchState.value = result
            }
        }
    }

}