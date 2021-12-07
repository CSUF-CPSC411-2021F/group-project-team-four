package com.example.skoolplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.skoolplanner.adapter.ActivityListAdapter
import com.example.skoolplanner.adapter.ActivityListener
import com.example.skoolplanner.database.Activity
import com.example.skoolplanner.database.ActivityDatabase
import com.example.skoolplanner.databinding.ActivityListBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

/**
 * A fragment that allows the user to input and clear Activity objects. These Activity entities are
 * displayed in a RecyclerView.
 */
class ActivityList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Creates data binding
        val binding: ActivityListBinding =
            DataBindingUtil.inflate(inflater, R.layout.activity_list, container, false)

        // Gets reference to application
        val application = requireNotNull(this.activity).application

        // Gets the Activity Dao
        val dataSource = ActivityDatabase.getInstance(application).activityDao

        // Creates a factory that generates ActivityViewModels connected to the database
        val viewModelFactory = ActivityViewModelFactory(dataSource, application)

        // Creates an ActivityViewModel
        val activityViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ActivityViewModel::class.java)

        // Connects the ActivityViewModel to the variable in the activity_list layout
        binding.activityViewModel = activityViewModel

        // Assigns the lifecycle owner to the activity
        binding.lifecycleOwner = this

        // A lambda function that is called when an item in the RecyclerView is tapped
        var activityAdapter = ActivityListAdapter(ActivityListener { activityId ->
            // Moves to the ActivityItemFragment view and provides the id of the activity
            // referenced by the select item in the RecyclerView
            this.findNavController().navigate(
                ActivityListDirections
                    .actionActivityListToActivityItemFragment(activityId)
            )
        })

        // Navigates from the ActivityList view to the CreateActivity view when "Add Activity"
        // button is clicked on
        //binding.createActivityButton.setOnClickListener { view: View ->
        //view.findNavController()
        //.navigate(R.id.action_activityList_to_createActivityFragment)
        //}

        // Associates the adapter with the RecyclerView
        binding.activityRecyclerview.adapter = activityAdapter

        // Submits an updated list to the activity listAdapter whenever changes are made
        activityViewModel.activityList.observe(viewLifecycleOwner, Observer {
            it?.let {
                activityAdapter.submitList(it)
            }
        })

        // Opens a date picker, allowing the user to set a due date for an Activity
        binding.dueDate.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Due Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            picker.show(childFragmentManager, "tag")

            picker.addOnPositiveButtonClickListener {
                // Sets date to the date value (picker.headerText) that the user chose
                var date = "${picker.headerText}"

                // Sets the text value of the dueDate TextView to date
                binding.dueDate.text = date
            }

        }


        binding.isExam.setOnCheckedChangeListener { _, isChecked ->
            binding.placeholder.text = ""
            if (!isChecked) {
                binding.isExam.text = "Assignment"
            } else {
                binding.isExam.text = "Exam"
            }
        }

        // Opens a time picker, allowing the user to set a time at which an Activity is due
        binding.dueTime.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .setTitleText("Select Time Due")
                .build()
            picker.show(childFragmentManager, "Tag")

            picker.addOnPositiveButtonClickListener {
                val hour: Int = picker.hour
                var newHour: Int = hour % 12
                val minute: Int = picker.minute
                var timeChosen: String = "12:10"


                // If the user inputs 0 for the hour, it is set to 12
                if (newHour == 0) {
                    newHour = 12
                }

                // Adds a zero to the minute value if user chose a number below 10 (for example,
                // converts 12:5 to 12:05)
                if (minute < 10) {
                    timeChosen = "${newHour}:0${picker.minute}"
                } else {
                    timeChosen = "${newHour}:${picker.minute}"
                }

                // Sets a period (AM or PM) for the time
                val clockPeriod: String = if (hour >= 12) {
                    "PM"
                } else {
                    "AM"
                }

                // Sets the text value of the dueTime TextView to time
                val time = "$timeChosen $clockPeriod"
                binding.dueTime.text = time
            }
        }
        // Return a link to the layout root
        return binding.root
    }

    fun getNumberOfActivities(activities: List<Activity>?): Int {
        val totalNumberOfActivities = activities!!.size

        return(totalNumberOfActivities)
    }
}