package com.example.room_library.room.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room_library.room.DAO.*
import com.example.room_library.room.Entities.Proyect
import com.example.room_library.room.Entities.UserxProyect
import com.genesis.ecogreen.room.Entities.Comment
import com.genesis.ecogreen.room.Entities.Task
import com.genesis.ecogreen.room.Entities.User
import com.genesis.ecogreen.room.Entities.UserxTask


@Database(entities = [Comment::class,Proyect::class,Task::class, User::class,UserxProyect::class,UserxTask::class], version = 3, exportSchema = false)
public abstract class LibraryDB: RoomDatabase(){

    abstract fun CommentDao() : CommentDao
    abstract fun ProyectDao() : ProyectDao
    abstract fun TaskDao() : TaskDao
    abstract fun UserDao() : UserDao
    abstract fun UserxProyectDao() : UserxProyectDao
    abstract fun UserxTaskDao() : UserxTaskDao

    //Todos los DAO

    companion object {
        @Volatile
        private var INSTANCE:LibraryDB? = null

        fun getInstance(Appcontext: Context): LibraryDB{
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance
            synchronized(this){
                val instance = Room
                    .databaseBuilder(Appcontext,LibraryDB::class.java,"Library_DB")
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }

}