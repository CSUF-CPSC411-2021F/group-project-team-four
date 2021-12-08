package com.example.skoolplanner.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ClassScheduleDao {
    // Add a class schedule entity to a table in the database.
    // We use suspend to run the function asynchronously (coroutine).
    @Insert
    suspend fun insert(classSchedule: ClassSchedule)

    // Update a class schedule entity to a table in the database. Often uses the primary key
    // We use suspend to run the function asynchronously (coroutine).
    @Update
    suspend fun update(classSchedule: ClassSchedule)

    // Custom query for retrieving a single Class Schedule from a table in the database using
    // its section id. We don't use suspend because LiveData objects are already designed
    // to work asynchronous.
    @Query("SELECT * from class_table WHERE secId = :key")
    fun get(key: Long): LiveData<ClassSchedule>?

    // Custom query for retrieving all ClassSchedule entities from a table in the database.
    // Data is stored to a List LiveData. We don't use suspend because LiveData objects
    // are already designed to work asynchronously.
    @Query("SELECT * from class_table ORDER BY secId DESC")
    fun getAllClassSchedules(): LiveData<List<ClassSchedule>>

    // Custom query for deleting all entities on a table in the database
    // We use suspend to run the function asynchronously (coroutine).
    @Query("DELETE from class_table")
    suspend fun clear()
}