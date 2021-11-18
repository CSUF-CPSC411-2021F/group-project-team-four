package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.skoolplanner.database.ActivityDao

/**
 * An ActivityItemViewModel that provides a connection to the database for storing and
 * retrieving corresponding values.
 */
class ActivityItemViewModel (
    val activityId: Long,
    val database: ActivityDao,
    application: Application
) : AndroidViewModel(application) {

    // Gets a LiveData-wrapped activity object given its ID
    val activity = database.get(activityId)
}