package edu.towson.cosc431.nicktaormino.fitnessapp

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_new_exercise.*
import java.util.*


class NewExerciseActivity: AppCompatActivity() {

    val receiver: BroadcastReceiver =object: BroadcastReceiver()
    {
        override fun onReceive(ctx: Context?, intent: Intent?) {


        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_new_exercise)

        set1_text.setOnClickListener {  }
        set2_text.setOnClickListener {  }
        set3_text.setOnClickListener {  }
        checkBox.setOnClickListener {  val intent=Intent(this,MyIntentService::class.java)
            startService(intent)}
        save_button.setOnClickListener{clickSave()}

    }

    fun clickSave(){
        val intent = Intent()
        val id = Date().date
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
        val intent2=Intent(this,MyIntentService::class.java)
        startService(intent2)
        finish()

    }

    companion object {
        val todo_extra_key = "exercise"
    }
    //todo Register
    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, IntentFilter(MyIntentService.BROADCAST_ACTION))

    }
    //todo unregister
    override fun onPause(){
        super.onPause()
        unregisterReceiver(receiver)
    }
}