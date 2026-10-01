package com.example.workouttracker

import androidx.room.Entity
import androidx.room.PrimaryKey

// This class represents one workout that gets saved in my database.
@Entity(tableName = "exercise_table")
data class ExerciseEntity(

    // Room automatically creates a different ID for each workout.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // These are the workout details that the user enters.
    val exerciseName: String,
    val duration: Int,
    val calories: Int
)
