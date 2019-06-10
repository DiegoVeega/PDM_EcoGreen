package com.example.room_library.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room_library.room.Entities.Project

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend = corrutina
    suspend fun insert(project: Project)

    @Query("SELECT * FROM Project")
    fun getAllProjects(): LiveData<List<Project>>

    @Query("UPDATE Project SET Project_done = 1 WHERE Project_name = :name")
    fun setProjectDone(name: String)

    @Query("UPDATE Project SET Project_description = :description  WHERE Project_name = :name")
    fun setProjectDescription(name: String,description:String)

    @Query("UPDATE Project SET Project_image = :image  WHERE Project_name = :name")
    fun setProjectImage(name: String,image:String)

}