package com.genesis.ecogreen.Room.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "UserxTask",
    foreignKeys = [
        ForeignKey(entity = User::class,
            parentColumns = arrayOf("User_mail"),
            childColumns = arrayOf("up_usermail")
        ),
        ForeignKey(
            entity = Task::class,
            parentColumns = arrayOf("Task_name"),
            childColumns = arrayOf("ut_taskname")
        )
    ]
)
data class UserxTask(
    @PrimaryKey
    var ut_usermail :String,
    @PrimaryKey
    var ut_taskname : String
)