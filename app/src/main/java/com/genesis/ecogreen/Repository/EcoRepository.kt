package com.genesis.ecogreen.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.room_library.room.DAO.*
import com.example.room_library.room.Entities.Project
import com.example.room_library.room.Entities.UserxProject
import com.genesis.ecogreen.Room.Entities.Comment
import com.genesis.ecogreen.Room.Entities.Task
import com.genesis.ecogreen.Room.Entities.User
import com.genesis.ecogreen.Room.Entities.UserxTask

class EcoRepository(private val CommentDao : CommentDao, private val ProjectDao: ProjectDao, private val TaskDao : TaskDao, private val UserDao : UserDao, private val UserxProjectDao : UserxProjectDao, private val UserxTaskDao : UserxTaskDao) {
    //GetAll
    val AllComments : LiveData<List<Comment>> = CommentDao.getAllComments()
    val AllProjects : LiveData<List<Project>> = ProjectDao.getAllProjects()
    val AllTasks : LiveData<List<Task>> = TaskDao.getAllTasks()
    val AllUsers : LiveData<List<User>> = UserDao.getAllUsers()
    val AllUserxProject : LiveData<List<UserxProject>> = UserxProjectDao.getAllUserxProject()

        //Algunas funciones de tablas cruz se mandan a llamar en otro lado porque necesitan un parametro
    val getAllUserxTask : LiveData<List<UserxTask>> = UserxTaskDao.getAllUserxTask()

        //Algunas funciones de tablas cruz se mandan a llamar en otro lado porque necesitan un parametro

    //Comment
    @WorkerThread
    suspend fun insert(comment: Comment) {
        CommentDao.insert(comment)
    }

    //Project
    @WorkerThread
    suspend fun insert(project: Project) {
        ProjectDao.insert(project)
    }

    fun setProjectDone(name:String){
        ProjectDao.setProjectDone(name)
    }
    fun setProjectDescription(name: String,description:String){
        ProjectDao.setProjectDescription(name,description)
    }
    fun setProjectImage(name: String,image:String){
        ProjectDao.setProjectImage(name,image)
    }

    //Task
    @WorkerThread
    suspend fun insert(task: Task) {
        TaskDao.insert(task)
    }
    fun setTaskDone(name: String){
        TaskDao.setTaskDone(name)
    }


    //User
    @WorkerThread
    suspend fun insert(user: User) {
        UserDao.insert(user)
    }

    //UserxProject
    @WorkerThread
    suspend fun insert(up: UserxProject) {
        UserxProjectDao.insert(up)
    }
    fun getAllUsersInThisProject(Projectname:String): LiveData<List<User>>{
        return UserxProjectDao.getAllUsersInThisProject(Projectname)
    }
    fun getAllProjectsFromThisUser(Usermail:String): LiveData<List<Project>>{
        return UserxProjectDao.getAllProjectsFromThisUser(Usermail)
    }


    //UserxTask
    @WorkerThread
    suspend fun insert(ut: UserxTask) {
        UserxTaskDao.insert(ut)
    }
    fun getAllUsersInThisTask(Taskname:String): LiveData<List<User>>{
        return UserxTaskDao.getAllUsersInThisTask(Taskname)
    }
    fun getAllTasksFromThisUser(Usermail:String): LiveData<List<Task>>{
        return UserxTaskDao.getAllTasksFromThisUser(Usermail)
    }


}