package com.example.skoolplanner

import android.provider.SyncStateContract.Helpers.insert
import android.service.autofill.Validators.not
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.skoolplanner.database.Activity
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityViewModelTest {

    private lateinit var activityViewModel: ActivityViewModel

    @Test
    fun insert() {

        //val activities = listOf<Activity>(
          //  Activity(1, "Name", "Dec 1, 2021", "Time: 12:10 PM", "")
        //)

        // Given a fresh ViewModel
        //val ActivityViewModel = ActivityViewModel(ApplicationProvider.getApplicationContext())

        activityViewModel.insert()

        val nameValue = activityViewModel.name

        assertEquals(nameValue, "")

        //assertEquals(value, "")

    }
}