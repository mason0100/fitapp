package edu.towson.cosc431.nicktaormino.fitnessapp




import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc431.nicktaormino.fitnessapp.IExerciseListController
//import edu.towson.cosc431.labsapp.interfaces.ISongController
//import edu.towson.cosc431.labsapp.models.Song
import kotlinx.android.synthetic.main.add_new_exercise.view.*
import kotlinx.android.synthetic.main.exercise_list_item.view.*


class ExerciseListAdapter(private val controller: IExerciseListController) : RecyclerView.Adapter<ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.add_new_exercise, parent, false)
        val viewHolder = ExerciseViewHolder(view)

        view.save_button.setOnClickListener {
            val position = viewHolder.adapterPosition
            val exercise = controller.exerciseList.getExercise(position)

            exercise.name = view.exercise_text.text.toString()
            exercise.set1 = view.set1_text.text.toString()
            exercise.set2 = view.set2_text.text.toString()
            exercise.set3 = view.set3_text.text.toString()
            controller.exerciseList.replace(position, exercise)

            this.notifyDataSetChanged()
        }


        view.checkBox.setOnClickListener {
            val position = viewHolder.adapterPosition
            controller.toggleIsCompleted(position)
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


    fun saveButton(){

    }


}

class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindExercise(exercise: ExerciseListItem) {
        itemView.exercise_text.setText(exercise.name)
        itemView.set1_text.setText(exercise.set1)
        itemView.set2_text.setText(exercise.set2)
        itemView.set3_text.setText(exercise.set3)
        itemView.checkBox.isChecked = exercise.isComplete
    }
}




