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
    var dueTime = MutableLiveData("") // Due Time Property of Activity
    var description = MutableLiveData("") // Description of Activity
    //var isExam = MutableLiveData("") // Activity type (assignment or exam)

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
            activity.dueTime = dueTime.value.toString()

            // If there was no input for description field, "No description." will be assigned to
            // the description property of the activity
            if(description.value.toString() == "")
            {
                activity.description = "No description."
            }
            else
            {
                activity.description = description.value.toString()
            }
            //activity.activityType = activityType.value.toString()

            if(activity.name.isNotBlank() && activity.dueDate.isNotBlank()
                && activity.dueTime.isNotBlank())
            {
                database.insert(activity)
            }
        }
    }

    fun getActivity(key: Long) {
        database.get(key)
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