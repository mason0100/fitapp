package edu.towson.cosc431.nicktaormino.fitnessapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.exercise_item.*
import kotlinx.android.synthetic.main.new_exercise_activity.*
import java.time.LocalDate

class NewExerciseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_exercise_activity)

        save_button.setOnClickListener{clickSave()}

    }

    fun clickSave(){
        val intent = Intent()
        val title: String = exercise_name.editableText.toString()
        val exercise1: Int = exercise1_new.editableText.toString().toInt()
        val exercise2: Int = exercise2_new.editableText.toString().toInt()
        val exercise3: Int = exercise3_new.editableText.toString().toInt()
        val isComplete : Boolean =checkBox_new.isChecked



        //create Todo_object
        val todo = ExerciseListItem(title, exercise1, exercise2, exercise3, isComplete)
        val json = Gson().toJson(todo)
        //
        intent.putExtra(todo_extra_key, json)
        setResult(Activity.RESULT_OK, intent)
        finish()

    }

    companion object {
        val todo_extra_key = "todo"
    }
}