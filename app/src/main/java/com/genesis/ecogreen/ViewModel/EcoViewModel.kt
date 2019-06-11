package com.genesis.ecogreen.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.room_library.room.Database.RoomDatabase
import com.example.room_library.room.Entities.Project
import com.example.room_library.room.Entities.UserxProject
import com.genesis.ecogreen.Repository.EcoRepository
import com.genesis.ecogreen.Room.Entities.Comment
import com.genesis.ecogreen.Room.Entities.Task
import com.genesis.ecogreen.Room.Entities.User
import com.genesis.ecogreen.Room.Entities.UserxTask
import kotlinx.coroutines.Dispatchers

class EcoViewModel (app: Application) : AndroidViewModel(app){
    private val Repository : EcoRepository
    val AllComments: LiveData<List<Comment>>
    val AllProjects: LiveData<List<Project>>
    val AllTasks: LiveData<List<Task>>
    val AllUsers: LiveData<List<User>>
    val AllUserxProject: LiveData<List<UserxProject>>

    init {
        val commentDao = RoomDatabase.getInstance(app).CommentDao()
        val projectDao = RoomDatabase.getInstance(app).ProjectDao()
        val taskDao = RoomDatabase.getInstance(app).TaskDao()
        val userDao = RoomDatabase.getInstance(app).UserDao()
        val userxProjectDao = RoomDatabase.getInstance(app).UserxProjectDao()
        val userxTaskDao = RoomDatabase.getInstance(app).UserxTaskDao()
        Repository = EcoRepository(commentDao, projectDao, taskDao, userDao,userxProjectDao, userxTaskDao)
        AllComments = Repository.AllComments
        AllProjects = Repository.AllProjects
        AllTasks = Repository.AllTasks
        AllUsers = Repository.AllUsers
        AllUserxProject = Repository.AllUserxProject
    }

    fun insert(comment: Comment) = ViewModelScope.launch(Dispatchers.IO){
        Repository.insert(comment)
    }

    fun insert(project: Project) = ViewModelScope.launch(Dispatchers.IO){
        Repository.insert(project)
    }

    fun insert(task: Task) = ViewModelScope.launch(Dispatchers.IO){
        Repository.insert(task)
    }

    fun insert(user: User) = ViewModelScope.launch(Dispatchers.IO){
        Repository.insert(user)
    }

    fun insert(userxProject: UserxProject) = ViewModelScope.launch(Dispatchers.IO){
        Repository.insert(userxProject)
    }

    fun insert(userxTask: UserxTask) = ViewModelScope.launch(Dispatchers.IO){
        Repository.insert(userxTask)
    }
}