package com.example.room_library.room.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.genesis.ecogreen.Room.Entities.User

@Entity(tableName = "UserxProject",
   /* foreignKeys = [
        ForeignKey(entity = User::class,
        parentColumns = arrayOf("User_mail"),
        childColumns = arrayOf("up_usermail")
        ),
        ForeignKey(
        entity = Project::class,
        parentColumns = arrayOf("Project_name"),
        childColumns = arrayOf("up_projectname")
        )
    ],*/
    primaryKeys = ["up_usermail","up_projectname"]
)
data class UserxProject(
    var up_usermail :String,
    var up_projectname : String
)