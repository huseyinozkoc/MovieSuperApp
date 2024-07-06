package com.example.mylibrary.movies.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.mylibrary.movies.common.Constants
import com.example.mylibrary.movies.data.source.remote.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("MoviesOkHttp")
    fun provideOkHttpClientForMovies(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(Constants.X_RAPIDAPI_KEY, Constants.RAPID_API_KEY_VALUE)
                    .addHeader(Constants.X_RAPIDAPI_HOST, Constants.RAPID_API_HOST_VALUE)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    @Named("MoviesRetrofit")
    fun provideRetrofitForMovies(@Named("MoviesOkHttp") okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideMovieService(@Named("MoviesRetrofit") retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)
}