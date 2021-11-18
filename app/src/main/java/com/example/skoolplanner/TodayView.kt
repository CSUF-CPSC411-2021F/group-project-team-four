package com.example.skoolplanner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.skoolplanner.adapter.TodayViewAdapter
import com.example.skoolplanner.databinding.FragmentTodayViewBinding
import java.text.DateFormat
import java.util.*
import com.example.skoolplanner.data.Datasource
import com.example.skoolplanner.model.Exam
import java.text.SimpleDateFormat


class TodayView : Fragment() {

    var clickedDate: String = ""

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
                sortActivities(parent.getItemAtPosition(position).toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val calendar = Calendar.getInstance()
        val calendarView: CalendarView = binding.simpleCalendarView
        var simpleDateFormat = SimpleDateFormat("MM/dd/yyyy")
        clickedDate = simpleDateFormat.format(Date())
        getActivities(clickedDate, binding)

        calendarView.setOnDateChangeListener { view, year, month, day ->
            calendar.set(year,month,day)

            clickedDate = simpleDateFormat.format(calendar.time)

            getActivities(clickedDate, binding)
        }
        
        return binding.root
    }


    /*
        This function will be called in order to populate the recycler view with the activities
        that are due for the current day that is selected in the calendar
    */
    fun getActivities(date: String, binding: FragmentTodayViewBinding){
        var activityList = binding.activityList

        //using temp dataset until database is complete
        val dataset = Datasource().loadExams()
        val filteredDataset = mutableListOf<Exam>()
        Log.d("DebugLog", "ClickedDate:")
        Log.d("DebugLog",clickedDate)
        for (i in dataset)
        {
            Log.d("DebugLog", "DataDate:")
            Log.d("DebugLog",i.date)
            if (i.date == date)
            {
                filteredDataset.add(i)
            }
        }
        activityList.adapter = TodayViewAdapter(filteredDataset)
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