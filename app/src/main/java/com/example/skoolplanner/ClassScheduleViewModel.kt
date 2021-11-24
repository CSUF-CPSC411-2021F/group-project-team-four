package com.example.skoolplanner

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.skoolplanner.database.ClassSchedule
import com.example.skoolplanner.database.ClassScheduleDao
import kotlinx.coroutines.launch

class ClassScheduleViewModel(
    val database: ClassScheduleDao, // Data access object for the ClassSchedule entity
    application: Application) : AndroidViewModel(application) {

        var secId = MutableLiveData(0L)
        var className = MutableLiveData("")
        var classDays = MutableLiveData("")
        var classStartTime = MutableLiveData("")
        var classEndTime = MutableLiveData("")

        // Retrieves all ClassSchedule objects from the database
        // Represented as a LiveData<List<ClassSchedule>>
        val classScheduleList = database.getAllClassSchedules()

//        /**
//         * Creates a LiveData<String> that contains information from all ClassSchedule objects.
//         * The Transformations.map function takes a LiveData object, performs operations on the
//         * object and returns a LiveData-wrapped object.
//         */
//        var classScheduleString = Transformations.map(classScheduleList) {
//                classSchedules -> // profiles refer to the underlying data List<ClassSchedule>
//            var result = ""
//            // Retrieve each ClassSchedule object from the list
//            for (classSchedule in classSchedules) {
//                // Create a string using the Profile name and location.
//                // The profile string is appended to a longer string with all profiles.
//                result += "${classSchedule.className} - ${classSchedule.classDays}\n"
//            }
//            // Returns the aggregated String that is wrapped by the map function in a LiveData object.
//            result
//        }

        /**
         * Inserts the Profile object into the database.
         */
        fun insert() {
            // Launch coroutines in the viewModelScope so that the coroutines are automatically
            // canceled when the ViewModel is destroyed.
            viewModelScope.launch {
                // Create ClassSchedule object using data stored in the EditText views
                var schedule = ClassSchedule()
                schedule.secId = secId.value!!
                schedule.className = className.value.toString()
                schedule.classDays = classDays.value.toString()
                schedule.classStartTime = classStartTime.value.toString()
                schedule.classEndTime = classEndTime.value.toString()

                // Insert data to the database using the insert coroutine.
                database.insert(schedule)
            }

        }

        /**
         * Deletes all Profile entities in the database.
         */
        fun clear() {
            // Launch coroutines in the viewModelScope so that the coroutines are automatically
            // canceled when the ViewModel is destroyed.
            viewModelScope.launch {
                // Delete data from the database using the clear coroutine.
                database.clear()
            }
        }
    }

