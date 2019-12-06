package edu.towson.cosc431.nicktaormino.fitnessapp
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button_add_exercise
import kotlinx.android.synthetic.main.activity_main.button_home
import kotlinx.android.synthetic.main.activity_main.button_login
import kotlinx.android.synthetic.main.previous_data.*

class MainActivity : AppCompatActivity(), IExerciseListController {
    // Todo11 Create a BroadcastReceiver(done)
    val receiver:BroadcastReceiver=object:BroadcastReceiver()
    {
        override fun onReceive(ctx: Context?, intent: Intent?) {
        button_notification.text="done"

        }
    }
    //public members of class
    override lateinit var exerciseList: ExerciseList
    lateinit var db: ExerciseDBRepository
    lateinit var cache: IExerciseCache


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidNetworking.initialize(this)

        //Data Items

        exerciseList = ExerciseList()

        val adapter = ExerciseListAdapter(this)

        recycler_exercise_list.adapter = adapter

        recycler_exercise_list.layoutManager =LinearLayoutManager(this)

        db = ExerciseDBRepository(this)
        cache = ExerciseCache()
        cache.refresh((db.getAll()))

        //Buttons
        button_home.setOnClickListener{}
        button_login.setOnClickListener {  }
        button_add_exercise.setOnClickListener { launchNewTodoActivity() }
        button_notification.setOnClickListener{ // TODO - 10. Start your service(done)
            val intent=Intent(this,MyIntentService::class.java)
            startService(intent)}
        adapter.notifyDataSetChanged()
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
                            db.addExercise(exercise)
                            cache.refresh(db.getAll())
                        }
                    }
                }

            }
            Activity.RESULT_CANCELED -> {
                Toast.makeText(this, "User Cancelled Exercise", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object{
        val NEW_EXERCISE_CODE = 1;
    }


    //Launch Activities









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
