package edu.fullerton.ecs.mdap.examdb.examlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.skoolplanner.database.Exam
import com.example.skoolplanner.database.ExamDao
import kotlinx.coroutines.launch

/**
 * examViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values.
 */
class ExamViewModel(
    val database: ExamDao, // Data access object for the exam entity
    application: Application
) : AndroidViewModel(application) {

    // Used to assign and retrieve name and location values from the interface.

    var className = MutableLiveData("")
    var name = MutableLiveData("")
    var date = MutableLiveData("")
    var time = MutableLiveData("")
    var description = MutableLiveData("")


    // Retrieves all exam objects from the database
    // Represented as a LiveData<List<exam>>
    val examList = database.getAllExams()

    /**
     * Inserts the exam object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create exam object using data stored in the EditText views
            var exam = Exam()
            exam.className = className.value.toString()
            exam.name = name.value.toString()
            exam.date = date.value.toString()
            exam.time = time.value.toString()
            exam.description = description.value.toString()


            // Insert data to the database using the insert coroutine.
            database.insert(exam)
        }
    }

}