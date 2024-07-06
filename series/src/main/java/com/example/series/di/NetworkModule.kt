package com.example.series.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.series.common.Constants
import com.example.series.data.SeriesService
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
    @Named("SeriesOkHttp")
    fun provideOkHttpClientForSeries(@ApplicationContext context: Context): OkHttpClient {
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
    @Named("SeriesRetrofit")
    fun provideRetrofitForSeries(@Named("SeriesOkHttp") okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideSeriesService(@Named("SeriesRetrofit") retrofit: Retrofit): SeriesService =
        retrofit.create(SeriesService::class.java)
}