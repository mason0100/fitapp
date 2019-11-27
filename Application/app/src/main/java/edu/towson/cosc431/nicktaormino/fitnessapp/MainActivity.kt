package edu.towson.cosc431.nicktaormino.fitnessapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button_add_exercise
import kotlinx.android.synthetic.main.activity_main.button_home
import kotlinx.android.synthetic.main.activity_main.button_login
import kotlinx.android.synthetic.main.previous_data.*

class MainActivity : AppCompatActivity(), IExerciseListController {

    //public members of class
    override lateinit var exerciseList: ExerciseList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Data Items

        exerciseList = ExerciseList()

        val adapter = ExerciseListAdapter(this)

        recycler_exercise_list.adapter = adapter

        recycler_exercise_list.layoutManager = LinearLayoutManager(this)


        //Buttons
        button_home.setOnClickListener{}
        button_login.setOnClickListener {  }
        button_add_exercise.setOnClickListener {  }
    }

    //Not Helper Functions

    fun launchNewTodoActivity(){
        //launches the new activity and saves data for the result
        val intent = Intent(this, NewExerciseActivity::class.java)
        startActivityForResult(intent,NEW_EXERCISE_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            Activity.RESULT_OK ->{
                when(requestCode){
                    NEW_EXERCISE_CODE -> {
                        val json: String? = data?.getStringExtra(NewExerciseActivity.todo_extra_key)
                        if (json != null){
                            val exercise: ExerciseListItem = Gson().fromJson<ExerciseListItem>(json, ExerciseListItem::class.java)
                            //add to log
                            //Need to add to list
                            Log.i("Exercise", exercise.toString())
                            exerciseList.addExercise(exercise)



                        }
                    }
                }

            }
            Activity.RESULT_CANCELED -> {
                Toast.makeText(this, "User Cancelled Todo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object{
        val NEW_EXERCISE_CODE = 1;
    }






//Helper Functions


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
