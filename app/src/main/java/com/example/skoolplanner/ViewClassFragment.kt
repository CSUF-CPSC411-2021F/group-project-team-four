package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.skoolplanner.databinding.FragmentViewClassBinding
import androidx.navigation.fragment.findNavController
import com.example.skoolplanner.adapter.ScheduleAdapter
import com.example.skoolplanner.adapter.ScheduleListener
import com.example.skoolplanner.database.ClassScheduleDatabase
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

/**
 * A simple [Fragment] subclass.
 * Use the [ViewClassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewClassFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create data binding
        val binding: FragmentViewClassBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_class, container, false)

        // Get reference to the application
        val application = requireNotNull(this.activity).application

        // Retrieve ClassSchedule data access object.
        val dataSource = ClassScheduleDatabase.getInstance(application).classScheduleDao

        // Create a factory that generates IntersectionViewModels connected to the database.
        val viewModelFactory = ScheduleViewModelFactory(dataSource, application)

        // Generate an ScheduleViewModel using the factory.
        val scheduleViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ScheduleViewModel::class.java)

        // Connect the ScheduleViewModel with the variable in the layout
        binding.scheduleViewModel = scheduleViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        // Provide a lambda function that is called when the RecyclerView item is selected.
        var scheduleAdapter = ScheduleAdapter(ScheduleListener {
                secId ->
            // Navigate to the schedule view and provide the id of the section referenced
            // by the select RecyclerView item.
            this.findNavController().navigate(
                ViewClassFragmentDirections
                    .actionViewClassFragmentToScheduleItemFragment(secId)
            )
        })

        // Attach schedule adapter.
        binding.scheduleList.adapter = scheduleAdapter

        // Submit an updated list to the schedule listAdapter whenever it changes. Take note
        // classScheduleList is a LiveData object.
        scheduleViewModel.classScheduleList.observe(viewLifecycleOwner, Observer {
            it?.let {
                scheduleAdapter.submitList(it)
            }
        })

        // Opens a time picker, allowing the user to set the Class Start Time
        binding.classTimeStart.setOnClickListener {
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
                if(minute < 10)
                {
                    timeChosen = "${newHour}:0${picker.minute}"
                }
                else
                {
                    timeChosen = "${newHour}:${picker.minute}"
                }

                // Sets a period (AM or PM) for the time
                val clockPeriod: String = if (hour >= 12) {
                    "PM"
                } else {
                    "AM"
                }

                // A string with the time chosen by the user
                val time = "$timeChosen $clockPeriod"

                // Sets the text value of the dueTime TextView to time
                binding.classTimeStart.text = time
            }
        }

        // Opens a time picker, allowing the user to set the Class Start Time
        binding.classTimeEnd.setOnClickListener {
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
                if(minute < 10)
                {
                    timeChosen = "${newHour}:0${picker.minute}"
                }
                else
                {
                    timeChosen = "${newHour}:${picker.minute}"
                }

                // Sets a period (AM or PM) for the time
                val clockPeriod: String = if (hour >= 12) {
                    "PM"
                } else {
                    "AM"
                }

                // A string with the time chosen by the user
                val time = "$timeChosen $clockPeriod"

                // Sets the text value of the dueTime TextView to time
                binding.classTimeEnd.text = time
            }
        }

        return binding.root
    }
}