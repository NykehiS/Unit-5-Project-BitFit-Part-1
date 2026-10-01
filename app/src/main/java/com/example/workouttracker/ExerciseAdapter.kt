package com.example.workouttracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// This adapter takes my saved workouts and displays them
// inside of the RecyclerView on the main screen.
class ExerciseAdapter(
    private var exercises: List<ExerciseEntity>
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // These connect to the TextViews in item_exercise.xml.
        val exerciseName: TextView =
            view.findViewById(R.id.exerciseNameTextView)

        val duration: TextView =
            view.findViewById(R.id.durationTextView)

        val calories: TextView =
            view.findViewById(R.id.caloriesTextView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseViewHolder {

        // This creates the layout for one workout in the list.
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)

        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ExerciseViewHolder,
        position: Int
    ) {

        // Gets the workout at the current position.
        val exercise = exercises[position]

        // Displays that workout's information.
        holder.exerciseName.text = exercise.exerciseName
        holder.duration.text =
            "Duration: ${exercise.duration} minutes"

        holder.calories.text =
            "Calories Burned: ${exercise.calories}"
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    // When I get new information from Room, this refreshes the list.
    fun updateExercises(newExercises: List<ExerciseEntity>) {
        exercises = newExercises
        notifyDataSetChanged()
    }
}