package edu.towson.cosc431.nicktaormino.fitnessapp

import android.content.Context

class ExerciseDBRepository(ctx:Context): IExerciseList {

    private val db: IDataBase

    init {
        db = ExerciseDatabase(ctx)
    }

    override fun addExercise(exercise: ExerciseListItem) {
        db.addExercise(exercise)
    }

    override fun getCount(): Int {
        return db.getExercises().size
    }

    override fun getExercise(id: Int): ExerciseListItem? {
        return db.getExercise(id)
    }

    override fun replace(idx: Int, exercise: ExerciseListItem) {
        db.updateExercise(exercise)
    }

    fun getAll(): List<ExerciseListItem> {
        return db.getExercises()
    }

    override fun deleteExercise(exercise: ExerciseListItem) {
        db.deleteExercise(exercise)
    }


}

class UserDBRepository(ctx:Context): IUserList {

    private val db: IDataBase2

    init {
        db = UserDatabase(ctx)
    }

    override fun addUser(user:User) {
        db.addUser(user)
    }

    override fun getCount(): Int {
        return db.getUsers().size
    }

    override fun getUser(id: String): User? {
        return db.getUser(id)
    }

    override fun replace(idx: String, user:User) {
        db.updateUser(user)
    }

    fun getAll(): List<User> {
        return db.getUsers()
    }

    override fun deleteUser(user: User) {
        db.deleteUser(user)
    }


}