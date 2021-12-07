package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.skoolplanner.database.ClassSchedule
import com.example.skoolplanner.database.ClassScheduleDao
import kotlinx.coroutines.launch

/**
 * ClassScheduleViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values. It retrieves the corresponding class
 * with the provided section ID.
 */
class ScheduleItemViewModel(
    val secId: Long,
    val database: ClassScheduleDao, // Data access object for the Intersection entity
    application: Application
) : AndroidViewModel(application) {

    // Retrieves a LiveData-wrapped class schedule object given its ID
    val schedule = database.get(secId)
}