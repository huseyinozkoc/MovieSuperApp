package com.example.mylibrary.movies.di

import android.content.Context
import com.example.mylibrary.movies.data.source.RequestTimeManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStorage {

    @Provides
    @Singleton
    fun provideUserManager(@ApplicationContext context: Context): RequestTimeManager {
        return RequestTimeManager(context)
    }
}