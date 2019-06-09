package com.example.room_library.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room_library.room.Entities.Proyect
import com.genesis.ecogreen.room.Entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend = corrutina
    suspend fun insert(user: User)

    @Query("SELECT * FROM User")
    fun getAllUsers(): LiveData<List<User>>


}