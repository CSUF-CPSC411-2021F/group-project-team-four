package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.skoolplanner.database.ClassSchedule
import com.example.skoolplanner.database.ClassScheduleDao
import kotlinx.coroutines.launch

class ScheduleViewModel(
    val database: ClassScheduleDao, // Data access object for the ClassSchedule entity
    application: Application
) : AndroidViewModel(application) {

    var className = MutableLiveData("")
    var classDays = MutableLiveData("")
    var classStartTime = MutableLiveData("")
    var classEndTime = MutableLiveData("")

    // Retrieves all ClassSchedule objects from the database
    // Represented as a LiveData<List<ClassSchedule>>
    val classScheduleList = database.getAllClassSchedules()

    /**
     * Inserts the Class Schedule object into the database.
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

            // Insert data to the database using the insert coroutine.
            if (schedule.className.isNotBlank() && schedule.classDays.isNotBlank() && schedule.classStartTime.isNotBlank() && schedule.classEndTime.isNotBlank()) {
                database.insert(schedule)
            }
        }

    }

    /**
     * Deletes all Profile entities in the database.
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