package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skoolplanner.database.ClassScheduleDao

/**
 * Generates an ScheduleViewModel tied to the database.
 */
class ScheduleViewModelFactory(
    private val dataSource: ClassScheduleDao, // Data access object
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the ScheduleViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleViewModel::class.java)) { // ViewModel class
            return ScheduleViewModel(dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}