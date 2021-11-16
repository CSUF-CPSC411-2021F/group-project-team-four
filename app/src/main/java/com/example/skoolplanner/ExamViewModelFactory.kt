package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skoolplanner.database.ExamDao
import edu.fullerton.ecs.mdap.examdb.examlist.ExamViewModel
import java.lang.IllegalArgumentException

/**
 * Generates an ExamViewModel tied to the database.
 */
class ExamViewModelFactory(
    private val dataSource: ExamDao,
    private val application: Application
) : ViewModelProvider.Factory {

    /**
     * Creates the ExamViewModel
     **/
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExamViewModel::class.java)) {
            return ExamViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}