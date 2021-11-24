package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.skoolplanner.database.Activity
import com.example.skoolplanner.database.ActivityDao
import kotlinx.coroutines.launch

/**
 * An ActivityViewModel used for data binding.
 */
class ActivityViewModel(

    val database: ActivityDao, // Data access object for an Activity entity
    application: Application
) : AndroidViewModel(application) {

    var name = MutableLiveData("") // Name property of Activity
    var dueDate = MutableLiveData("") // Due date property of Activity

    // Gets all Activity objects from the database
    val activityList = database.getAllActivities()

    /**
     * Inserts an Activity object into the database.
     */
    fun insert() {
        viewModelScope.launch {
            // Create Activity object
            var activity = Activity()

            activity.name = name.value.toString()
            activity.dueDate = dueDate.value.toString()

            database.insert(activity)
        }
    }

    /**
     * Deletes all Activity objects from the database
     */
    fun clear() {
        viewModelScope.launch {
            database.clear()
        }
    }
}