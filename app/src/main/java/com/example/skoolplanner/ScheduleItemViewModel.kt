package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.skoolplanner.database.ClassScheduleDao

/**
 * ScheduleItemViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values. It retrieves the corresponding class
 * with the provided section ID.
 */
class ScheduleItemViewModel(
    val secId: Long,
    val database: ClassScheduleDao, // Data access object for the ClassSchedule entity
    application: Application
) : AndroidViewModel(application) {

    // Retrieves a LiveData-wrapped class schedule object given its ID
    val schedule = database.get(secId)
}