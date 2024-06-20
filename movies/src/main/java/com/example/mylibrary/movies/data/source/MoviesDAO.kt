package com.example.mylibrary.movies.data.source



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mylibrary.movies.data.entities.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDAO {

    @Query("SELECT * FROM Movie")
    fun getAllMovies(): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("DELETE FROM Movie")
    suspend fun deleteAllMovies()
}