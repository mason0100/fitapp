package edu.towson.cosc431.nicktaormino.fitnessapp




import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc431.nicktaormino.fitnessapp.IExerciseListController
//import edu.towson.cosc431.labsapp.interfaces.ISongController
//import edu.towson.cosc431.labsapp.models.Song
import kotlinx.android.synthetic.main.add_new_exercise.view.*


class ExerciseListAdapter(private val controller: IExerciseListController) : RecyclerView.Adapter<ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.add_new_exercise, parent, false)
        val viewHolder = ExerciseViewHolder(view)

        view.save_button.setOnClickListener {

        }
        view.checkBox.setOnClickListener {

        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return controller.exerciseList.getCount()
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val song = controller.exerciseList.getExercise(position)
        holder.bindExercise(song)
    }
}

class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindExercise(exercise: ExerciseListItem) {
        itemView.exercise_text.text = exercise.name
        itemView.set1_text.setText(exercise.set1)
        itemView.set2_text.setText(exercise.set2)
        itemView.set3_text.setText(exercise.set3)
        itemView.checkBox.isChecked = exercise.isComplete
    }
}




/*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.add_new_exercise.view.*
import edu.towson.cosc431.nicktaormino.fitnessapp.IExerciseListController
import edu.towson.cosc431.nicktaormino.fitnessapp.ExerciseListItem


class ExerciseListAdapter(private val controller: IExerciseListController): RecyclerView.Adapter<ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.exercise_list_item, parent, false)
        val viewHolder = ExerciseViewHolder(view)

        view.checkBox.setOnClickListener {
            val position = viewHolder.adapterPosition
            controller.toggleIsCompleted(position)
            this.notifyItemChanged(position)
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return controller.exerciseList.getCount()
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val todo: ExerciseListItem = controller.exerciseList.getExercise(position)
        holder.bindExercise(todo)
    }





}

class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindExercise(exercise: ExerciseListItem) {
        itemView.exercise_text.text = exercise.name
        itemView.set1_text.setText(exercise.set1)
        itemView.set2_text.setText(exercise.set2)
        itemView.set3_text.setText(exercise.set3)
        itemView.checkBox.isChecked = exercise.isComplete
    }

}*/