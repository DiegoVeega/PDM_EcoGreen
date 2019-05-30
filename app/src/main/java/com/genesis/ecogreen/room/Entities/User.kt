package com.genesis.ecogreen.room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id : Long =0,
    @PrimaryKey
    @ColumnInfo(name = "User_mail")var usermail : String,
    @ColumnInfo(name = "User_name")var name : String,
    @ColumnInfo(name = "User_pass")var pass : String,
    @ColumnInfo(name = "User_type")var type : Int
)

