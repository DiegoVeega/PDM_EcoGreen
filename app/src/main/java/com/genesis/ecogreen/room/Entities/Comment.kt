package com.genesis.ecogreen.room.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "Comment",
    foreignKeys = [
        ForeignKey(entity = Task::class,
            parentColumns = arrayOf("Task_name"),
            childColumns = arrayOf("Comment_task")
        )
    ]
)
data class Comment(
    @PrimaryKey(autoGenerate = true)
    var id : Long =0,
    @ColumnInfo(name = "Comment_user")var user : User,
    @ColumnInfo(name = "Comment_comment")var comment : String,
    @ColumnInfo(name = "Comment_date")var date : String,
    @ColumnInfo(name = "Comment_task")var task: String

)

/*
private Calendar calendar;
private SimpleDateFormat dateFormat;
private String date;
calendar = Calendar.getInstance();
dateFormat = new SimpleDateFormat("MM/dd/yyyy");
date = simpleDateFormat.format(calendar.getTime());
dateTimeDisplay.setText(date);


*/
