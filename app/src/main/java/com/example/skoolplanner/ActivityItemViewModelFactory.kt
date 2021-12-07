package edu.fullerton.ecs.mdap.intersectiondb.intersectionitem

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skoolplanner.ActivityItemViewModel
import com.example.skoolplanner.database.ActivityDao

/**
 * Generates an ActivityItemViewModel
 */
class ActivityItemViewModelFactory(
    private val activityId: Long,
    private val dataSource: ActivityDao, // Data access object
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the ActivityItemViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityItemViewModel::class.java)) { // ViewModel class
            return ActivityItemViewModel(
                activityId,
                dataSource,
                application
            ) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}