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

        activityViewModel.insert()

        val nameValue = activityViewModel.name

        assertEquals(nameValue, "")
    }
}