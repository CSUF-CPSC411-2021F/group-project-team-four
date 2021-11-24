package com.example.skoolplanner.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * A data access object for the Activity entity. This interface defines how data is stored, updated,
 * retrieved, and deleted from the Activity database.
 */
@Dao
interface ActivityDao {

    // Adds an Activity entity to a table in the database
    @Insert
    suspend fun insert(activity: Activity)

    // Updates an Activity entity in a table in the database
    @Update
    suspend fun update(activity: Activity)

    // Gets an individual Activity entity from a table in the database
    @Query("SELECT * from activity_table WHERE activityId = :key")
    fun get(key: Long): LiveData<Activity>?

    // Gets all Activity entities from a table in the database
    @Query("SELECT * from activity_table ORDER BY activityId DESC")
    fun getAllActivities(): LiveData<List<Activity>>

    // Deletes all Activity entities in a table in the database
    @Query("DELETE from activity_table")
    suspend fun clear()


}