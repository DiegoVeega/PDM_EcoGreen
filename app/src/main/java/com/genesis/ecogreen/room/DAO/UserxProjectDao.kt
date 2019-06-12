package com.example.room_library.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room_library.room.Entities.Project
import com.example.room_library.room.Entities.UserxProject
import com.genesis.ecogreen.Room.Entities.User

@Dao
interface UserxProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend = corrutina
    suspend fun insert(up: UserxProject)

    @Query("SELECT * FROM UserxProject")
    fun getAllUserxProject(): LiveData<List<UserxProject>>

    @Query("SELECT U.* FROM User U INNER JOIN UserxProject ON User_mail=up_usermail WHERE up_projectname=:Projectname")
    fun getAllUsersInThisProject(Projectname:String): LiveData<List<User>>

    @Query("SELECT P.* FROM Project P INNER JOIN UserxProject ON Project_name=up_projectname WHERE up_usermail=:Usermail")
    fun getAllProjectsFromThisUser(Usermail:String): LiveData<List<Project>>

}