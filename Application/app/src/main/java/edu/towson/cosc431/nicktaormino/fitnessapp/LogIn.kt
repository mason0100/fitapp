package edu.towson.cosc431.nicktaormino.fitnessapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.add_new_exercise.*
import kotlinx.android.synthetic.main.log_in.*
import java.util.*

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)
        SignIn.setOnClickListener { click() }
        CreateUser.setOnClickListener { click() }
    }

    fun click(){
        val intent = Intent()
        val user: String = UserName.editableText.toString()
        val password: String = PassWord.editableText.toString()




        //create Todo_object
        val todo = User(user,password)
        val json = Gson().toJson(todo)
        //
        intent.putExtra(LogIn.todo_extra_key, json)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        val todo_extra_key = "log"
    }
}