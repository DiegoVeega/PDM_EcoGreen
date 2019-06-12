package com.genesis.ecogreen.Room.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "UserxTask"
    ,
    /*foreignKeys = [
        ForeignKey(entity = User::class,
            parentColumns = arrayOf("User_mail"),
            childColumns = arrayOf("ut_usermail")
        ),
        ForeignKey(
            entity = Task::class,
            parentColumns = arrayOf("Task_name"),
            childColumns = arrayOf("ut_taskname")
        )
    ],*/
    primaryKeys = [
    "ut_usermail","ut_taskname"]
)
data class UserxTask(
    var ut_usermail :String,
    var ut_taskname : String
)