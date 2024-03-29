package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skoolplanner.database.ActivityDao

/**
 * Generates an ActivityViewModel associated with the database
 */
class ActivityViewModelFactory(
    private val dataSource: ActivityDao, // Data access object
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the ActivityViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) { // ViewModel class
            return ActivityViewModel(dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
