package com.genesis.ecogreen.Repository

import androidx.lifecycle.LiveData
import com.example.room_library.room.DAO.*
import com.example.room_library.room.Entities.Project
import com.example.room_library.room.Entities.UserxProject
import com.genesis.ecogreen.Room.Entities.Comment
import com.genesis.ecogreen.Room.Entities.Task
import com.genesis.ecogreen.Room.Entities.User

class EcoRepository(private val CommentDao : CommentDao, private val ProjectDao: ProjectDao, private val TaskDao : TaskDao, private val UserDao : UserDao, private val UserxProjectDao : UserxProjectDao, private val UserxTaskDao : UserxTaskDao) {
    

}