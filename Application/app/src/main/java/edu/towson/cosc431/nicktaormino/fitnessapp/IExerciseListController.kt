package edu.towson.cosc431.nicktaormino.fitnessapp

interface IExerciseListController {

        fun deleteExercise(idx: Int)
        fun toggleIsCompleted(idx: Int)
        val exerciseList: ExerciseList
        val exerciseCache: IExerciseCache
}
