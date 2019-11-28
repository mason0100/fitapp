package edu.towson.cosc431.nicktaormino.fitnessapp

interface IExerciseList {

    fun addExercise(exercise:ExerciseListItem)
    fun deleteExercise(exercise:ExerciseListItem)
    fun getCount(): Int
    fun getExercise(idx: Int): ExerciseListItem?
    fun replace(idx: Int, exercise:ExerciseListItem)

}
interface IExerciseCache{
    fun exerciseCount(): Int
    fun getExercise(position: Int): ExerciseListItem
    fun refresh(songs:List<ExerciseListItem>)
}