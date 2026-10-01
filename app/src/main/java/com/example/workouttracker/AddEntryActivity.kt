package com.example.workouttracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddEntryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_entry)

        // Connects my Kotlin code to the input fields in the XML.
        val exerciseEditText =
            findViewById<EditText>(R.id.exerciseEditText)

        val durationEditText =
            findViewById<EditText>(R.id.durationEditText)

        val caloriesEditText =
            findViewById<EditText>(R.id.caloriesEditText)

        val saveButton =
            findViewById<Button>(R.id.saveButton)

        // Gets access to my Room database.
        val database = ExerciseDatabase.getDatabase(this)

        saveButton.setOnClickListener {

            // Gets what the user entered into each field.
            val exerciseName =
                exerciseEditText.text.toString().trim()

            val durationText =
                durationEditText.text.toString().trim()

            val caloriesText =
                caloriesEditText.text.toString().trim()

            // I do not want the user to save an incomplete workout.
            if (
                exerciseName.isEmpty() ||
                durationText.isEmpty() ||
                caloriesText.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Please fill out all fields.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val duration = durationText.toIntOrNull()
            val calories = caloriesText.toIntOrNull()

            // This makes sure the number fields contain valid numbers.
            if (duration == null || calories == null) {

                Toast.makeText(
                    this,
                    "Please enter valid numbers.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Creates a new workout using the information entered.
            val newExercise = ExerciseEntity(
                exerciseName = exerciseName,
                duration = duration,
                calories = calories
            )

            // Database operations should not run on the main thread,
            // so I save the workout using the IO dispatcher.
            lifecycleScope.launch(IO) {

                database.exerciseDao().insertExercise(newExercise)

                withContext(Main) {

                    Toast.makeText(
                        this@AddEntryActivity,
                        "Workout saved!",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Goes back to the main screen after saving.
                    finish()
                }
            }
        }
    }
}