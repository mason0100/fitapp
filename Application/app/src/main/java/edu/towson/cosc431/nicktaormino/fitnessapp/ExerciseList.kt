// Authors: Michael Mason, Nick Taormino


package edu.towson.cosc431.nicktaormino.fitnessapp

import java.util.*

class ExerciseList: IExerciseList {

    //Exercise list for recycler view
    private var exerciseList: MutableList<ExerciseListItem> = mutableListOf()

    //Initialize List for testing
    //REMEMBR TO DELET INIT LIST**************************!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!DELETE
    init {

        // val dateCreated: String = now().toString()
        var temp = ExerciseListItem(1,"Bench", "1", "2", "3", false)

        exerciseList.add(temp)
        exerciseList.add(temp)
        exerciseList.add(temp)
        exerciseList.add(temp)
        exerciseList.add(temp)
        exerciseList.add(temp)
        exerciseList.add(temp)
        exerciseList.add(temp)


    }



    override fun addExercise(exercise: ExerciseListItem) {
        exerciseList.add(exercise)



    }

    override fun deleteExercise(exercise: ExerciseListItem) {
        exerciseList.remove(exercise)
    }

    override fun getExercise(idx: Int): ExerciseListItem {
        return exerciseList.get(idx)
    }

    override fun replace(idx: Int, exercise: ExerciseListItem) {
        if(idx >= exerciseList.size) throw Exception("Outside of bounds")
        exerciseList[idx] = exercise
    }


    override fun getCount(): Int {
        return exerciseList.size
    }







}

class ExerciseCache : IExerciseCache {
    override fun exerciseCount(): Int {
        return exercise.size
    }

    override fun getExercise(position: Int): ExerciseListItem {
        return exercise.get(position)
    }

    override fun refresh(songs: List<ExerciseListItem>) {
        this.exercise.clear()
        this.exercise.addAll(songs)
    }

    private var exercise: MutableList<ExerciseListItem> = mutableListOf()

}