package com.example.moviesewa.modules


import com.example.moviesewa.mvvm.MovieRepository
import com.example.moviesewa.mvvm.MovieServiceApi
import com.example.moviesewa.mvvm.RepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModule {

    companion object {
        @Provides
        @Singleton
        fun provideApi(retrofit: Retrofit): MovieServiceApi {
            return retrofit.create(MovieServiceApi::class.java)
        }

        @Provides
        @Singleton
        fun retrofitObject(okHttpClient: OkHttpClient,@BaseUrl baseUrl: String): Retrofit {
            return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseUrl).build()
        }

        @Provides
        @Singleton
        fun provideHttpClient(@AuthToken token: String): OkHttpClient {
            val interceptor = Interceptor { chain ->
                val newRequest = chain.request().newBuilder().addHeader(
                    "Authorization",
                    "Bearer $token"
                )
                    .addHeader("accept", "application/json")
                    .build()

                chain.proceed(newRequest)
            }
            return OkHttpClient.Builder().addInterceptor(interceptor).build()
        }

        @Provides
        @Singleton
        @AuthToken
        fun provideToken(): String {
            return "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3OGNlYzlkNWIzMzc4MWRkNzA1MDljYTBlNmU4ODAxOSIsInN1YiI6IjY0YmU5MjZmYjg2NWViMDBmZjRkYzczZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jnE3a8uulaWHYV41pYdcFFvalkKTJbaBKYBajz-a1XU"
        }


        @Provides
        @Singleton
        @BaseUrl
        fun provideBaseUrl() : String
        {
            return "https://api.themoviedb.org/3/"
        }
    }

    @Binds
    @Singleton
    abstract fun provideRepository(movieRepository: MovieRepository): RepositoryInterface

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthToken

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl



