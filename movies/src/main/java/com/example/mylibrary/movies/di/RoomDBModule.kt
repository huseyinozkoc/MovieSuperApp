package com.example.mylibrary.movies.di

import android.content.Context
import androidx.room.Room
import com.example.mylibrary.movies.data.source.MoviesDAO
import com.example.mylibrary.movies.data.source.MoviesRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideMoviesRoomDB(@ApplicationContext appContext: Context): MoviesRoomDB =
        Room.databaseBuilder(
            appContext,
            MoviesRoomDB::class.java,
            "movies.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideMoviesDAO(moviesRoomDB: MoviesRoomDB): MoviesDAO =
        moviesRoomDB.moviesDAO()
}