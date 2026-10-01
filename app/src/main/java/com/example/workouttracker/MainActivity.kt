package com.example.workouttracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ExerciseAdapter
    private lateinit var database: ExerciseDatabase

    private lateinit var emptyTextView: TextView

    // These TextViews display the user's workout statistics.
    private lateinit var totalWorkoutsTextView: TextView
    private lateinit var averageDurationTextView: TextView
    private lateinit var averageCaloriesTextView: TextView
    private lateinit var totalCaloriesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView =
            findViewById<RecyclerView>(R.id.exerciseRecyclerView)

        val addEntryButton =
            findViewById<Button>(R.id.addEntryButton)

        emptyTextView =
            findViewById(R.id.emptyTextView)

        // Connects my progress dashboard to the TextViews in the XML.
        totalWorkoutsTextView =
            findViewById(R.id.totalWorkoutsTextView)

        averageDurationTextView =
            findViewById(R.id.averageDurationTextView)

        averageCaloriesTextView =
            findViewById(R.id.averageCaloriesTextView)

        totalCaloriesTextView =
            findViewById(R.id.totalCaloriesTextView)

        adapter = ExerciseAdapter(emptyList())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        database = ExerciseDatabase.getDatabase(this)

        addEntryButton.setOnClickListener {

            // Opens the screen where a new workout can be entered.
            val intent = Intent(
                this,
                AddEntryActivity::class.java
            )

            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        // Reloading here makes sure my workout list and progress
        // update after a new workout is saved.
        loadExercises()
    }

    private fun loadExercises() {

        lifecycleScope.launch(IO) {

            val exercises =
                database.exerciseDao().getAllExercises()

            withContext(Main) {

                // Updates the workout history.
                adapter.updateExercises(exercises)

                // Updates the averages and progress information.
                updateProgress(exercises)

                if (exercises.isEmpty()) {
                    emptyTextView.visibility = View.VISIBLE
                } else {
                    emptyTextView.visibility = View.GONE
                }
            }
        }
    }

    private fun updateProgress(exercises: List<ExerciseEntity>) {

        // If there are no workouts yet, I keep every statistic at zero.
        if (exercises.isEmpty()) {

            totalWorkoutsTextView.text = "0"
            averageDurationTextView.text = "0 min"
            averageCaloriesTextView.text = "0"
            totalCaloriesTextView.text = "0"

            return
        }

        // Gets the total number of workouts that have been saved.
        val totalWorkouts = exercises.size

        // Adds together all of the workout durations.
        val totalDuration = exercises.sumOf {
            it.duration
        }

        // Adds together all calories burned.
        val totalCalories = exercises.sumOf {
            it.calories
        }

        // Calculates the average duration of a workout.
        val averageDuration =
            totalDuration / totalWorkouts

        // Calculates the average calories burned per workout.
        val averageCalories =
            totalCalories / totalWorkouts

        // Displays all of the calculated progress information.
        totalWorkoutsTextView.text =
            totalWorkouts.toString()

        averageDurationTextView.text =
            "$averageDuration min"

        averageCaloriesTextView.text =
            averageCalories.toString()

        totalCaloriesTextView.text =
            totalCalories.toString()
    }
}