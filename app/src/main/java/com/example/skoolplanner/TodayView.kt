package com.example.skoolplanner

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import java.text.DateFormat
import java.util.*


class TodayView : Fragment() {

    val sortPriority: String = "Priority"
    val sortDate: String = "Date"
    val sortAlpha: String = "A-Z"
    val sortRevAlpha: String = "Z-A"


    var clickedDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.fragment_today_view,container,false)

        // Find spinner and attach sort options to it
        val spinner: Spinner = binding.root.findViewById(R.id.sort_spinner)
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
                sortActivities(parent.getItemAtPosition(position).toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val calendar = Calendar.getInstance()
        val calendarView: CalendarView = binding.root.findViewById(R.id.simpleCalendarView)
        val dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT)
        clickedDate = dateFormatter.format(Date())
        Log.d("DebugLog", "Starting Date:")
        Log.d("DebugLog",clickedDate)

        calendarView.setOnDateChangeListener { view, year, month, day ->
            calendar.set(year,month,day)

            clickedDate = dateFormatter.format(calendar.time)
            Log.d("DebugLog", "Clicked Date:")
            Log.d("DebugLog",clickedDate)
        }

        
        return binding.root
    }


    /*
        This function will be called in order to populate the recycler view with the activities
        that are due for the current day that is selected in the calendar
    */
    fun getActivities(){

    }

    /*
        This function will be called when the user sorts the activities
    */
    fun sortActivities(sortType: String){
        Log.d("DebugLog", "Clicked Spinner option:")
        Log.d("DebugLog", sortType)
        when (sortType) {
            //TODO: finish 'when' block when sort companion is complete
        }
    }

}