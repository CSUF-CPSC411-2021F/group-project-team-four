package com.example.skoolplanner.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "class_table")
data class ClassSchedule(

    // Defines the table's primary key.
    @PrimaryKey(autoGenerate = true)
    var secId: Long = 0L,

    // Non-primary key column.
    @ColumnInfo()
    var className: String = "",

    // Non-primary key column.
    @ColumnInfo()
    var classDays: String = "",

    // Non-primary key column.
    @ColumnInfo()
    var classStartTime: String = "",

    // Non-primary key column.
    @ColumnInfo()
    var classEndTime: String = ""
)