package com.example.skoolplanner.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class for Exam Entity
 */

@Entity(tableName = "exam_table")
data class Exam(

    @PrimaryKey(autoGenerate = true)
    var examId: Long = 0L,

    @ColumnInfo
    var className: String = "",

    @ColumnInfo
    var name: String = "",

    @ColumnInfo
    var date: String = "",

    @ColumnInfo
    var time: String = "",

    @ColumnInfo
    var description: String = ""
)
