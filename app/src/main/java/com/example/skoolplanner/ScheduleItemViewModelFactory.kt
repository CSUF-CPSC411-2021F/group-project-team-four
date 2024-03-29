package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skoolplanner.database.ClassScheduleDao

/**
 * Generates an ScheduleItemViewModel tied to the database.
 */
class ScheduleItemViewModelFactory(
    private val secId: Long,
    private val dataSource: ClassScheduleDao, // Data access object
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the ScheduleItemViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleItemViewModel::class.java)) { // ViewModel class
            return ScheduleItemViewModel(
                secId,
                dataSource,
                application
            ) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}