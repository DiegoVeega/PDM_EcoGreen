package com.genesis.ecogreen.Room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "Comment"
/*,
    foreignKeys = [
        ForeignKey(entity = Task::class,
            parentColumns = arrayOf("Task_name"),
            childColumns = arrayOf("Comment_task")
        ),
        ForeignKey(entity = User::class,
            parentColumns = arrayOf("User_mail"),
            childColumns = arrayOf("Comment_user")
        )
    ]*/
)
data class Comment(

    @ColumnInfo(name = "Comment_user")var user : String,
    @ColumnInfo(name = "Comment_comment")var comment : String,
    @ColumnInfo(name = "Comment_date")var date : String,
    @ColumnInfo(name = "Comment_task")var task: String

){
    @PrimaryKey(autoGenerate = true)
    var id : Long =0
}
