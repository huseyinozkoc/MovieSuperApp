package com.example.mylibrary.movies.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mylibrary.movies.data.entities.Movie
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MoviesRoomDB : RoomDatabase() {
    abstract fun moviesDAO(): MoviesDAO
}



class Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}