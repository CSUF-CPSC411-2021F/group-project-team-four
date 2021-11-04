package com.example.skoolplanner.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

/**
 * A data access object for the Activity entity. This interface defines how data is stored, updated,
 * retrieved, and deleted from the Activity database.
 */
@Dao
interface ActivityDao {

    @Insert
    suspend fun insert(activity: Activity)

    @Update
    suspend fun update(activity: Activity)


}