package com.example.room_library.room.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room_library.room.DAO.*
import com.example.room_library.room.Entities.Project
import com.example.room_library.room.Entities.UserxProyect
import com.genesis.ecogreen.Room.Entities.Comment
import com.genesis.ecogreen.Room.Entities.Task
import com.genesis.ecogreen.Room.Entities.User
import com.genesis.ecogreen.Room.Entities.UserxTask


@Database(entities = [Comment::class,Project::class,Task::class, User::class,UserxProyect::class,UserxTask::class], version = 3, exportSchema = false)
public abstract class RoomDatabase: RoomDatabase(){

    abstract fun CommentDao() : CommentDao
    abstract fun ProyectDao() : ProjectDao
    abstract fun TaskDao() : TaskDao
    abstract fun UserDao() : UserDao
    abstract fun UserxProyectDao() : UserxProjectDao
    abstract fun UserxTaskDao() : UserxTaskDao

    //Todos los DAO

    companion object {
        @Volatile
        private var INSTANCE:RoomDatabase? = null

        fun getInstance(Appcontext: Context): RoomDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room
                    .databaseBuilder(Appcontext,RoomDatabase::class.java,"Library_DB")
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }

}