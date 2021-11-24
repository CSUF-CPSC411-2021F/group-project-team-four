package com.example.skoolplanner

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.skoolplanner.database.ClassScheduleDao

class ClassScheduleViewModelFactory(
    private val dataSource: ClassScheduleDao, // Data access object
    private val application: Application): ViewModelProvider.Factory {

        /**
         * Creates the ClassScheduleViewModel
         */
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ClassScheduleViewModel::class.java)) { // ViewModel class
                return ClassScheduleViewModel(dataSource, application) as T // Call ViewModel constructor
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }