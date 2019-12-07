package edu.towson.cosc431.nicktaormino.fitnessapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in)}

    companion object {
        val todo_extra_key = "log"
    }
}