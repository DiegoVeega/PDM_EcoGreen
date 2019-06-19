package com.example.room_library.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.genesis.ecogreen.Room.Entities.Comment

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun insert(comment : Comment)

    @Query("SELECT * FROM Comment")
    fun getAllComments(): LiveData<List<Comment>>
}