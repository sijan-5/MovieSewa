package com.example.moviesewa.data_classes

data class MovieData(val posterPath : String, val title : String, val releaseData:String, val movieId:Int=0)
data class SearchItems(val posterPath : String, val title : String, val releaseData:String, val overView: String)