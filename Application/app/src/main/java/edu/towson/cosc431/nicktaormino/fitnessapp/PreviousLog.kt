package edu.towson.cosc431.nicktaormino.fitnessapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.previous_data.*
import java.util.*

class PreviousLog: AppCompatActivity(), IExerciseListController{
    var date: Int? = null
    lateinit var db: ExerciseDBRepository
    override lateinit var exerciseList: ExerciseList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.previous_data)

        db = ExerciseDBRepository(this)
        exerciseList = ExerciseList()

        val adapter = ExerciseListAdapter(this)
        previous_data.adapter = adapter
        previous_data.layoutManager = LinearLayoutManager(this)


        calendarView.setOnClickListener{
            date = calendarView.date.toInt()
            val Data = db.getAll()
            val list = ExerciseList()
            var x = 0
            while(x < Data.size && date != null)
            {
                if(Data.get(x).id == date)
                {
                   list.addExercise(Data.get(x))
                    adapter.notifyDataSetChanged()
                }
                x++
            }
            exerciseList = list
        }

        adapter.notifyDataSetChanged()

    }


    companion object {
        val todo_extra_key = "log"
    }

    override fun deleteExercise(idx: Int) {
        val current = exerciseList.getExercise(idx)
        exerciseList.deleteExercise(current)

    }

    override fun toggleIsCompleted(idx: Int) {
        val exercise = exerciseList.getExercise(idx)
        val newExercise = exercise.copy(isComplete = !exercise.isComplete)
        exerciseList.replace(idx, newExercise)
    }

}