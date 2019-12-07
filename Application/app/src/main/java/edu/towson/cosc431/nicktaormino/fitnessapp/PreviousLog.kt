package edu.towson.cosc431.nicktaormino.fitnessapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.previous_data.*
import java.util.*

class PreviousLog: AppCompatActivity(){
    lateinit var date: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.previous_data)

        calendarView.setOnClickListener{
            date = calendarView.date.toString()
        }
    }


    companion object {
        val todo_extra_key = "log"
    }

}