package com.example.room_library.room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Project")
data class Project(
    @ColumnInfo(name = "Project_name")var name : String,
    @ColumnInfo(name = "Project_description")var description: String,
    @ColumnInfo(name = "Project_done")var done: Int,
    @ColumnInfo(name = "Project_creation")var creation: String,
    @ColumnInfo(name = "Project_completion")var completion: String,
    @ColumnInfo(name = "Project_image")var image: String
){
    @PrimaryKey(autoGenerate = true)
    var id : Long =0
}