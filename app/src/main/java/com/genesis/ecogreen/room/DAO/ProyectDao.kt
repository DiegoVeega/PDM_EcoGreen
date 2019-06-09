package com.example.room_library.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room_library.room.Entities.Proyect

@Dao
interface ProyectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend = corrutina
    suspend fun insert(proyect: Proyect)

    @Query("SELECT * FROM Proyect")
    fun getAllProyects(): LiveData<List<Proyect>>

    @Query("UPDATE Proyect SET Proyect_done = 1 WHERE Proyect_name = :name")
    fun setProyectDone(name: String)

    @Query("UPDATE Proyect SET Proyect_description = :description  WHERE Proyect_name = :name")
    fun setProyectDescription(name: String,description:String)

    @Query("UPDATE Proyect SET Proyect_image = :image  WHERE Proyect_name = :name")
    fun setProyectImage(name: String,image:String)

}