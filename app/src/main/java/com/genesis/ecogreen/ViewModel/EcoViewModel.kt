package com.genesis.ecogreen.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room_library.room.Database.LibraryDB
import com.example.room_library.room.Entities.Project
import com.example.room_library.room.Entities.UserxProject
import com.genesis.ecogreen.Repository.EcoRepository
import com.genesis.ecogreen.Room.Entities.Comment
import com.genesis.ecogreen.Room.Entities.Task
import com.genesis.ecogreen.Room.Entities.User
import com.genesis.ecogreen.Room.Entities.UserxTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EcoViewModel (app: Application) : AndroidViewModel(app){
    private val Repository : EcoRepository
    val AllComments: LiveData<List<Comment>>
    val AllProjects: LiveData<List<Project>>
    val AllTasks: LiveData<List<Task>>
    val AllUsers: LiveData<List<User>>
    val AllUserxProject: LiveData<List<UserxProject>>

    init {
        val commentDao = LibraryDB.getInstance(app).CommentDao()
        val projectDao = LibraryDB.getInstance(app).ProjectDao()
        val taskDao = LibraryDB.getInstance(app).TaskDao()
        val userDao = LibraryDB.getInstance(app).UserDao()
        val userxProjectDao = LibraryDB.getInstance(app).UserxProjectDao()
        val userxTaskDao = LibraryDB.getInstance(app).UserxTaskDao()
        Repository = EcoRepository(commentDao, projectDao, taskDao, userDao,userxProjectDao, userxTaskDao)
        AllComments = Repository.AllComments
        AllProjects = Repository.AllProjects
        AllTasks = Repository.AllTasks
        AllUsers = Repository.AllUsers
        AllUserxProject = Repository.AllUserxProject
    }

    fun insert(comment: Comment) = viewModelScope.launch(Dispatchers.IO){
        Repository.insert(comment)
    }

    fun insert(project: Project) = viewModelScope.launch(Dispatchers.IO){
        Repository.insert(project)
    }

    fun insert(task: Task) = viewModelScope.launch(Dispatchers.IO){
        Repository.insert(task)
    }

    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO){
        Repository.insert(user)
    }

    fun insert(userxProject: UserxProject) = viewModelScope.launch(Dispatchers.IO){
        Repository.insert(userxProject)
    }

    fun insert(userxTask: UserxTask) = viewModelScope.launch(Dispatchers.IO){
        Repository.insert(userxTask)
    }
}