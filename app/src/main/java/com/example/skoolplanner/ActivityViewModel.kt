package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.skoolplanner.database.Activity
import com.example.skoolplanner.database.ActivityDao
import kotlinx.coroutines.launch

/**
 * An ActivityViewModel that connects to the database and uses data binding
 */
class ActivityViewModel(

    val database: ActivityDao, // Data access object for an Activity entity
    application: Application
) : AndroidViewModel(application) {

    var name = MutableLiveData("") // Name property of Activity
    var dueDate = MutableLiveData("") // Due date property of Activity
    var dueTime = MutableLiveData("") // Due Time Property of Activity
    var description = MutableLiveData("") // Description of Activity

    // Gets all Activity objects from the database
    val activityList = database.getAllActivities()

    /**
     * Adds an Activity entity to the database
     */
    fun insert() {
        viewModelScope.launch {
            // Creates an Activity object
            var activity = Activity()

            // Data is collected from input fields
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

            // Checks to ensure that the name, due date, and due time fields are not empty
            // before inserting the activity entity into the database
            if(activity.name.isNotBlank() && activity.dueDate.isNotBlank()
                && activity.dueTime.isNotBlank())
            {
                // Inserts the Activity entity into the database
                database.insert(activity)
            }
        }
    }

    /**
     * Clears all Activity entities from the database
     */
    fun clear() {
        viewModelScope.launch {
            // Deletes data from the database
            database.clear()
        }
    }
}