package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.skoolplanner.database.ClassSchedule
import com.example.skoolplanner.database.ClassScheduleDao
import kotlinx.coroutines.launch

/**
 * ScheduleViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values.
 */
class ScheduleViewModel(
    val database: ClassScheduleDao, // Data access object for the ClassSchedule entity
    application: Application
) : AndroidViewModel(application) {

    var className = MutableLiveData("")
    var classDays = MutableLiveData("")
    var classStartTime = MutableLiveData("")
    var classEndTime = MutableLiveData("")

    // Retrieves all ClassSchedule objects from the database
    val classScheduleList = database.getAllClassSchedules()

    /**
     * Inserts the ClassSchedule object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create ClassSchedule object using data stored in the EditText views
            var schedule = ClassSchedule()
            schedule.className = className.value.toString()
            schedule.classDays = classDays.value.toString()
            schedule.classStartTime = classStartTime.value.toString()
            schedule.classEndTime = classEndTime.value.toString()

            // Makes sure the EditText views are not blank.
            if (schedule.className.isNotBlank() && schedule.classDays.isNotBlank() && schedule.classStartTime.isNotBlank() && schedule.classEndTime.isNotBlank()) {
                // Insert data to the database using the insert coroutine.
                database.insert(schedule)
            }
        }

    }

    /**
     * Deletes all ClassSchedule entities in the database.
     */
    fun clear() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.clear()
        }
    }
}