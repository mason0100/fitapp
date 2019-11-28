package edu.towson.cosc431.nicktaormino.fitnessapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.add_new_exercise.*
import java.util.*


class NewExerciseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_exercise)

        set1_text.setOnClickListener {  }
        set2_text.setOnClickListener {  }
        set3_text.setOnClickListener {  }
        save_button.setOnClickListener{clickSave()}

    }

    fun clickSave(){
        val intent = Intent()
        val id = Date().toString()
        val title: String = exercise_text.editableText.toString()
        val exercise1: String = set1_text.editableText.toString()
        val exercise2: String = set2_text.editableText.toString()
        val exercise3: String = set3_text.editableText.toString()
        val isComplete : Boolean =checkBox.isChecked




        //create Todo_object
        val todo = ExerciseListItem(id,title, exercise1, exercise2, exercise3, isComplete)
        val json = Gson().toJson(todo)
        //
        intent.putExtra(todo_extra_key, json)
        setResult(Activity.RESULT_OK, intent)
        finish()

    }

    companion object {
        val todo_extra_key = "exercise"
    }
}