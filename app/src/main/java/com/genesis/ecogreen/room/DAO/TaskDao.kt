package com.example.room_library.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room_library.room.Entities.Proyect
import com.genesis.ecogreen.room.Entities.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend = corrutina
    suspend fun insert(task: Task)

    @Query("SELECT * FROM Task")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("UPDATE Task SET Task_done = 1 WHERE Task_name = :name")
    fun setTaskDone(name: String)


}