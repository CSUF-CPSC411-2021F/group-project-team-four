package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.skoolplanner.adapter.ActivityListAdapter
import com.example.skoolplanner.adapter.ActivityListener
import com.example.skoolplanner.database.ActivityDatabase
import com.example.skoolplanner.databinding.ActivityListBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * A fragment that allows the user to input and clear Activity objects, and displays this list of
 * Activities in a RecyclerView
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
                this, viewModelFactory).get(ActivityViewModel::class.java)

        //val spinner: Spinner = binding.activityType
        //ArrayAdapter.createFromResource(
          //  requireContext(),
            //R.array.activity_type_options,
            //android.R.layout.simple_spinner_item
        //).also { adapter ->
          //  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            //spinner.adapter = adapter
        //}

        // Connects the ActivityViewModel to the variable in the activity_list layout
        binding.activityViewModel = activityViewModel

        // Assigns the lifecycle owner to the activity
        binding.lifecycleOwner = this

        // A lambda function that is called when an item in the RecyclerView is tapped
        var activityAdapter = ActivityListAdapter(ActivityListener {
            activityId ->
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

        binding.dueDate.setOnClickListener {
            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Due Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            picker.show(childFragmentManager, "tag")

            picker.addOnPositiveButtonClickListener {
                var date = "${picker.headerText}"
                binding.dueDate.text = date
            }

        }

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
                if (newHour == 0) {
                    newHour = 12
                }

                // If the user chose a minute value below 10 (such as 5), make sure to add a zero
                // value (05)
                if(minute < 10)
                {
                    timeChosen = "${newHour}:0${picker.minute}"
                }
                else
                {
                    timeChosen = "${newHour}:${picker.minute}"
                }

                val clockPeriod: String = if (hour >= 12) {
                    "PM"
                } else {
                    "AM"
                }

                val time = "Time: $timeChosen $clockPeriod"
                binding.dueTime.text = time
            }
        }

        // Return a link to the layout root
        return binding.root
    }

    fun sortList() {

    }
}