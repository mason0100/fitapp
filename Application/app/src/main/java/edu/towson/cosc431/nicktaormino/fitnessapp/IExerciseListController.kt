package edu.towson.cosc431.nicktaormino.fitnessapp

interface IExerciseListController {

        fun deleteTodo(idx: Int)
        fun toggleIsCompleted(idx: Int)
        val exercise: ExerciseListItem
}