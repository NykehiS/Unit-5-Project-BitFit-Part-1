package com.example.workouttracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// This creates the Room database that stores all of my workouts.
@Database(
    entities = [ExerciseEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ExerciseDatabase : RoomDatabase() {

    // This gives the rest of my app access to the DAO functions.
    abstract fun exerciseDao(): ExerciseDao

    companion object {

        @Volatile
        private var INSTANCE: ExerciseDatabase? = null

        fun getDatabase(context: Context): ExerciseDatabase {

            // I only create the database once and reuse the same instance
            // whenever another part of the app needs it.
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseDatabase::class.java,
                    "exercise_database"
                ).build()

                INSTANCE = instance

                instance
            }
        }
    }
}