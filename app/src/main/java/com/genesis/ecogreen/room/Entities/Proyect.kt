package com.example.room_library.room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Proyect")
data class Proyect(
    @PrimaryKey(autoGenerate = true)
    var id : Long =0,
    @PrimaryKey
    @ColumnInfo(name = "Proyect_name")var name : String,
    @ColumnInfo(name = "Proyect_description")var description: String,
    @ColumnInfo(name = "Proyect_done")var done: Int,
    @ColumnInfo(name = "Proyect_creation")var creation: String,
    @ColumnInfo(name = "Proyect_completion")var completion: String,
    @ColumnInfo(name = "Proyect_image")var image: String
)