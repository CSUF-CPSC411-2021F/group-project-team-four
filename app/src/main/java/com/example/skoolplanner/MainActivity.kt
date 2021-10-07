package com.example.skoolplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_exam)

        val timePicker: TextView = findViewById(R.id.select_time)
        timePicker.setOnClickListener {
            openTimePicker()
        }

        val datePicker: TextView = findViewById(R.id.select_date)
        datePicker.setOnClickListener {
            openDatePicker()
        }
    }

    private fun openDatePicker() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .build()
        picker.show(supportFragmentManager, "tag")
    }

    private fun openTimePicker() {
        val clockFormat = TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Pick date and time")
            .build()
        picker.show(supportFragmentManager, "Tag")
    }


}