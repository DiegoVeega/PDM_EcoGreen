package com.example.room_library.room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.genesis.ecogreen.room.Entities.Comment
import com.genesis.ecogreen.room.Entities.Task
import com.genesis.ecogreen.room.Entities.User

@Entity(tableName = "UserxProyect",
    foreignKeys = [
        ForeignKey(entity = User::class,
        parentColumns = arrayOf("User_mail"),
        childColumns = arrayOf("up_usermail")
        ),
        ForeignKey(
        entity = Proyect::class,
        parentColumns = arrayOf("Proyect_name"),
        childColumns = arrayOf("up_proyectname")
        )
    ]
)
data class UserxProyect(
    @PrimaryKey
    var up_usermail :String,
    @PrimaryKey
    var up_proyectname : String
)