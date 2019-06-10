package com.genesis.ecogreen.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id : Long =0,
    @PrimaryKey
    @ColumnInfo(name = "Task_name")var name : String,
    @ColumnInfo(name = "Task_creation")var creation : String,
    @ColumnInfo(name = "Task_completion")var completion : String,
    @ColumnInfo(name = "Task_done")var done : Int,
    @ColumnInfo(name = "Task_description")var description : String

)


