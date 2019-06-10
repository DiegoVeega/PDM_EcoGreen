package com.example.room_library.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room_library.room.Entities.Proyect
import com.example.room_library.room.Entities.UserxProyect
import com.genesis.ecogreen.Room.Entities.User

@Dao
interface UserxProyectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend = corrutina
    suspend fun insert(up: UserxProyect)

    @Query("SELECT * FROM UserxProyect")
    fun getAllUserxProyect(): LiveData<List<UserxProyect>>

    @Query("SELECT User_mail,User_name,User_pass,User_type FROM User INNER JOIN UserxProyect ON User_mail=up_usermail WHERE up_proyectname=:Proyectname")
    fun getAllUsersInThisProject(Proyectname:String): LiveData<List<User>>

    @Query("SELECT Proyect_name,Proyect_description,Proyect_done,Proyect_creation,Proyect_completion,Proyect_image FROM Proyect INNER JOIN UserxProyect ON Proyect_name=up_proyectname WHERE up_usermail=:Usermail")
    fun getAllProyectsFromThisUser(Usermail:String): LiveData<List<Proyect>>

}