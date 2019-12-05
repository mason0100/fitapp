package edu.towson.cosc431.nicktaormino.fitnessapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object ExerciseContract{
    object ExerciseEntry: BaseColumns{
        const val TABLE_NAME = "exercises"
        const val COLUMN_NAME_NAME = "exercise_name"
        const val COLUMN_NAME_SET1 = "exercise_set1"
        const val COLUMN_NAME_SET2 = "exercise_set2"
        const val COLUMN_NAME_SET3 = "exercise_set3"
        const val COLUMN_NAME_COMPLETED = "exercise_completed"
        const val COLUMN_NAME_DELETED = "deleted"
    }
}

interface IDataBase{
    fun addExercise(exercise: ExerciseListItem)
    fun getExercises(): List<ExerciseListItem>
    fun getExercise(id:Int): ExerciseListItem?
    fun deleteExercise(exercise:ExerciseListItem)
    fun updateExercise(exercise:ExerciseListItem)
}

private const val CREATE_SONG_TABLE = "CREATE TABLE ${ExerciseContract.ExerciseEntry.TABLE_NAME} (" +
        "${BaseColumns._ID} STRING PRIMARY KEY, " +
        "${ExerciseContract.ExerciseEntry.COLUMN_NAME_NAME} TEXT, " +
        "${ExerciseContract.ExerciseEntry.COLUMN_NAME_SET1} TEXT, " +
        "${ExerciseContract.ExerciseEntry.COLUMN_NAME_SET2} TEXT, " +
        "${ExerciseContract.ExerciseEntry.COLUMN_NAME_SET3} TEXT, " +
        "${ExerciseContract.ExerciseEntry.COLUMN_NAME_COMPLETED} BOOL, " +
        "${ExerciseContract.ExerciseEntry.COLUMN_NAME_DELETED} BOOL DEFAULT 0" +
        ")"

private const val DELETE_EXERCISE_TABLE = "DROP TABLE IF EXISTS ${ExerciseContract.ExerciseEntry.TABLE_NAME}"

class ExerciseDatabase(ctx: Context): IDataBase
{

    class exerciseDBHelper(ctx: Context): SQLiteOpenHelper(ctx, DATABASE_NAME,null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(CREATE_SONG_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL(DELETE_EXERCISE_TABLE)
            onCreate(db)
        }

        companion object {
            val DATABASE_NAME = "exercise.db"
            val DATABASE_VERSION = 1
        }

    }
    private val db: SQLiteDatabase

    init {
        db = exerciseDBHelper(ctx).writableDatabase
    }

    override fun addExercise(exercise: ExerciseListItem) {
        val cvs = toContentValues(exercise)
        db.insert(ExerciseContract.ExerciseEntry.TABLE_NAME, null, cvs)
    }

    override fun getExercises(): List<ExerciseListItem> {
        val project = arrayOf(BaseColumns._ID,ExerciseContract.ExerciseEntry.COLUMN_NAME_NAME,ExerciseContract.ExerciseEntry.COLUMN_NAME_SET1,ExerciseContract.ExerciseEntry.COLUMN_NAME_SET2,ExerciseContract.ExerciseEntry.COLUMN_NAME_SET3,ExerciseContract.ExerciseEntry.COLUMN_NAME_COMPLETED)
        val sortOrder = "${BaseColumns._ID} ASC"
        val selection = "${ExerciseContract.ExerciseEntry.COLUMN_NAME_DELETED} = ?"
        val selectionArg = arrayOf("0")

        val cursor = db.query(
            ExerciseContract.ExerciseEntry.TABLE_NAME,
            project,
            selection,
            selectionArg,
            null,
            null,
            sortOrder


        )
        val exercises = mutableListOf<ExerciseListItem>()
        with(cursor)
        {
            while(cursor.moveToNext()){
                val id = getString(getColumnIndex(BaseColumns._ID))
                val name = getString(getColumnIndex(ExerciseContract.ExerciseEntry.COLUMN_NAME_NAME))
                val set1 = getString(getColumnIndex(ExerciseContract.ExerciseEntry.COLUMN_NAME_SET1))
                val set2 = getString(getColumnIndex(ExerciseContract.ExerciseEntry.COLUMN_NAME_SET2))
                val set3 = getString(getColumnIndex(ExerciseContract.ExerciseEntry.COLUMN_NAME_SET3))
                val awesome = getInt(getColumnIndex(ExerciseContract.ExerciseEntry.COLUMN_NAME_COMPLETED)) > 0
                val exercise = ExerciseListItem(id,name,set1,set2,set3,awesome)
                exercises.add(exercise)
            }
        }
        return exercises
    }

    override fun getExercise(id: Int): ExerciseListItem? {
        val project = arrayOf(BaseColumns._ID,ExerciseContract.ExerciseEntry.COLUMN_NAME_NAME,ExerciseContract.ExerciseEntry.COLUMN_NAME_SET1,ExerciseContract.ExerciseEntry.COLUMN_NAME_SET2,ExerciseContract.ExerciseEntry.COLUMN_NAME_SET3,ExerciseContract.ExerciseEntry.COLUMN_NAME_COMPLETED)
        val sortOrder = "${BaseColumns._ID} ASC"
        val selection = "${ExerciseContract.ExerciseEntry.COLUMN_NAME_DELETED} = ?"
        val selectionArg = arrayOf("0", id.toString())

        val cursor = db.query(
            ExerciseContract.ExerciseEntry.TABLE_NAME,
            project,
            selection,
            selectionArg,
            null,
            null,
            sortOrder


        )
        val exercises = mutableListOf<ExerciseListItem>()
        with(cursor)
        {
            while(cursor.moveToNext()){
                val id = getString(getColumnIndex(BaseColumns._ID))
                val name = getString(getColumnIndex(ExerciseContract.ExerciseEntry.COLUMN_NAME_NAME))
                val set1 = getString(getColumnIndex(ExerciseContract.ExerciseEntry.COLUMN_NAME_SET1))
                val set2 = getString(getColumnIndex(ExerciseContract.ExerciseEntry.COLUMN_NAME_SET2))
                val set3 = getString(getColumnIndex(ExerciseContract.ExerciseEntry.COLUMN_NAME_SET3))
                val awesome = getInt(getColumnIndex(ExerciseContract.ExerciseEntry.COLUMN_NAME_COMPLETED)) > 0
                val exercise = ExerciseListItem(id,name,set1,set2,set3,awesome)
                exercises.add(exercise)
            }
        }
        if(exercises.size == 1) return exercises[0]
        else return null
    }

    override fun deleteExercise(exercise: ExerciseListItem) {
        val cvs = toContentValues(exercise)
        cvs.put(ExerciseContract.ExerciseEntry.COLUMN_NAME_DELETED, "1")
        val selection = "${BaseColumns._ID} = ?"
        val selectionArg = arrayOf(exercise.id.toString())
        db.update(ExerciseContract.ExerciseEntry.TABLE_NAME ,cvs, selection, selectionArg)
    }

    override fun updateExercise(exercise: ExerciseListItem) {
        val cvs = toContentValues(exercise)
        val selection = "${BaseColumns._ID} = ?"
        val selectionArg = arrayOf(exercise.id.toString())
        db.update(ExerciseContract.ExerciseEntry.TABLE_NAME , cvs, selection, selectionArg)
    }

}

private fun toContentValues(exercise: ExerciseListItem): ContentValues{
    val cv = ContentValues()
    cv.put(BaseColumns._ID, exercise.id)
    cv.put(ExerciseContract.ExerciseEntry.COLUMN_NAME_NAME, exercise.name)
    cv.put(ExerciseContract.ExerciseEntry.COLUMN_NAME_SET1, exercise.set1)
    cv.put(ExerciseContract.ExerciseEntry.COLUMN_NAME_SET2, exercise.set2)
    cv.put(ExerciseContract.ExerciseEntry.COLUMN_NAME_SET3, exercise.set3)
    cv.put(ExerciseContract.ExerciseEntry.COLUMN_NAME_COMPLETED, exercise.isComplete)
    return cv
}
