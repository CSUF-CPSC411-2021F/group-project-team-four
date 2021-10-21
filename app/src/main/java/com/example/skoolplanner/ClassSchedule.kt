package com.example.skoolplanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.skoolplanner.adapter.ScheduleAdapter
import com.example.skoolplanner.databinding.ActivityMainBinding

class ClassSchedule : AppCompatActivity(){

    var class_name : String = ""
    var class_day : String = ""
    var class_time : Int = 0

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        var scheduleAdapter = ScheduleAdapter(this)
//        binding.scheduleList.adapter = scheduleAdapter
    }

    fun getClassInfo() {
        // TODO: This function gets the inputs of the user and stores them accordingly
    }

    fun display() {
        // TODO: This function displays the classes the user has inputted and their time and day
    }
}