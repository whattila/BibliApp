package com.example.bibliapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bibliapp.data.database.dao.ChapterDAO
import com.example.bibliapp.data.database.entities.Chapter

@Database(entities = [Chapter::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun chapterDao(): ChapterDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "chapter_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
