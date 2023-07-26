package com.example.moviesewa.modules


import com.example.moviesewa.mvvm.MovieRepository
import com.example.moviesewa.mvvm.MovieServiceApi
import com.example.moviesewa.mvvm.RepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideApi() : MovieServiceApi {
//        return ApiImpl()
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://api.themoviedb.org/3/").build()
            .create(MovieServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api : MovieServiceApi) : RepositoryInterface
    {
        return MovieRepository(api)
    }
}