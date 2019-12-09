package edu.towson.cosc431.nicktaormino.fitnessapp

interface IExerciseList {

    fun addExercise(exercise:ExerciseListItem)
    fun deleteExercise(exercise:ExerciseListItem)
    fun getCount(): Int
    fun getExercise(idx: Int): ExerciseListItem?
    fun replace(idx: Int, exercise:ExerciseListItem)

}
interface IUserList{
    fun addUser(user: User)
    fun deleteUser(user: User)
    fun getCount(): Int
    fun getUser(user:String): User?
    fun replace(user:String, us:User)
}
interface IExerciseCache{
    fun exerciseCount(): Int
    fun getExercise(position: Int): ExerciseListItem
    fun refresh(songs:List<ExerciseListItem>): MutableList<ExerciseListItem>
}
interface IUserCache{
    fun getCount(): Int
    fun getExercise(position: Int): User
    fun refresh(users:List<User>)
}