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
    val AllCommets : LiveData<List<Comment>> = CommentDao.getAllComments()
    val AllProjects : LiveData<List<Project>> = ProjectDao.getAllProjects()
    val AllTasks : LiveData<List<Task>> = TaskDao.getAllTasks()
    val AllUsers : LiveData<List<User>> = UserDao.getAllUsers()
    val AllUserxProject : LiveData<List<UserxProject>> = UserxProjectDao.getAllUserxProject()

        //Revisen si esta bien el parametro recibido "String()" tengo algunas dudas.
        val getAllUsersInThisProject : LiveData<List<Project>> = UserxProjectDao.getAllProjectsFromThisUser(String())
        val getAllProjectsFromThisUser : LiveData<List<User>> = UserxProjectDao.getAllUsersInThisProject(String())

    val getAllUserxTask : LiveData<List<UserxTask>> = UserxTaskDao.getAllUserxTask()

        //Revisen si esta bien el parametro recibido "String()" tengo algunas dudas.
        val getAllUsersInThisTask : LiveData<List<User>> = UserxTaskDao.getAllUsersInThisTask(String())
        val getAllTasksFromThisUser : LiveData<List<Task>> = UserxTaskDao.getAllTasksFromThisUser(String())

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

            //Incluir funciones set de Project Equipo Genesis, </3



    //Task
    @WorkerThread
    suspend fun insert(task: Task) {
        TaskDao.insert(task)
    }

            //Incluir funciones set de Project Equipo Genesis, </3


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

    //UserxTask
    @WorkerThread
    suspend fun insert(ut: UserxTask) {
        UserxTaskDao.insert(ut)
    }
}