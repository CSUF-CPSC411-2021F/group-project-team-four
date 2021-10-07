package com.example.skoolplanner

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


class TodayView : Fragment() {

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
    fun sortActivities(){

    }

}