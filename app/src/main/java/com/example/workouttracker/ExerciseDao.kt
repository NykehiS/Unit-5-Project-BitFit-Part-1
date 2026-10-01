package com.example.workouttracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDao {

    // Saves one new workout into the database.
    @Insert
    suspend fun insertExercise(exercise: ExerciseEntity)

    // Gets all of my saved workouts with the newest one first.
    @Query("SELECT * FROM exercise_table ORDER BY id DESC")
    suspend fun getAllExercises(): List<ExerciseEntity>
}