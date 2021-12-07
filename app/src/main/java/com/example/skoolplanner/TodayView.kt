package com.example.skoolplanner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.findNavController
import com.example.skoolplanner.adapter.ActivityListAdapter
import com.example.skoolplanner.adapter.ActivityListener
import com.example.skoolplanner.adapter.TodayViewAdapter
import com.example.skoolplanner.databinding.FragmentTodayViewBinding
import java.text.DateFormat
import java.util.*
import com.example.skoolplanner.data.Datasource
import com.example.skoolplanner.database.ActivityDatabase
import com.example.skoolplanner.databinding.ActivityListBinding
import com.example.skoolplanner.model.Exam
import java.text.SimpleDateFormat
import androidx.lifecycle.Observer
import com.example.skoolplanner.database.Activity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class TodayView : Fragment() {

    var clickedDate: String = ""
    var sortOption: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTodayViewBinding.inflate(inflater, container, false)

        // Find spinner and attach sort options to it
        val spinner: Spinner = binding.sortSpinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sort_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // Call sort function when spinner item is selected
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                //sortActivities(parent.getItemAtPosition(position).toString())
                sortOption = parent.getItemAtPosition(position).toString()
                getActivities(clickedDate, binding, sortOption)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // find the calander and update clicked date based on which date is clicked
        val calendar = Calendar.getInstance()
        val calendarView: CalendarView = binding.simpleCalendarView
        var simpleDateFormat = SimpleDateFormat("MMM d, yyyy")
        clickedDate = simpleDateFormat.format(Date())
        getActivities(clickedDate, binding, sortOption)

        calendarView.setOnDateChangeListener { view, year, month, day ->
            calendar.set(year,month,day)

            clickedDate = simpleDateFormat.format(calendar.time)

            getActivities(clickedDate, binding, sortOption)
        }
        
        return binding.root
    }


    /*
        getActivities is called whenever the user selects a new date or new sort option, it updates
        the recycler view with all activities from the current day that is selected
    */
    fun getActivities(date: String, binding: FragmentTodayViewBinding, sort: String) {
        val application = requireNotNull(this.activity).application
        val dataSource = ActivityDatabase.getInstance(application).activityDao
        val viewModelFactory = ActivityViewModelFactory(dataSource, application)
        val activityViewModel = ViewModelProvider(this, viewModelFactory).get(ActivityViewModel::class.java)

        var activityAdapter = ActivityListAdapter(ActivityListener {
            activityId ->
            this.findNavController().navigate(
                TodayViewDirections.actionTodayViewToActivityItemFragment(activityId)
            )
        })

        binding.activityList.adapter = activityAdapter

        // Observer for activity database
        activityViewModel.activityList.observe(viewLifecycleOwner, Observer { it ->
            it?.let { it ->
                val newActivityList = mutableListOf<Activity>()

                // Check if activity is in the current selected date
                for (activity in it)
                {
                    if (activity.dueDate == date)
                    {
                        newActivityList.add(activity)
                    }
                }
                // Sort list based on sort option selected
                when (sort) {
                    "Name" -> newActivityList.sortBy { it.name }
                    "Time" -> newActivityList.sortBy {
                        val formatter = SimpleDateFormat("hh:mm a", Locale.US)
                        val date = formatter.parse(it.dueTime)
                        date
                    }
                    else -> newActivityList.sortByDescending { it.activityId }
                }
                activityAdapter.submitList(newActivityList)
            }
        })
    }

}