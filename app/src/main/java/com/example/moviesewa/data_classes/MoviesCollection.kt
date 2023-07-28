package com.example.moviesewa.data_classes

data class MoviesCollection(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)