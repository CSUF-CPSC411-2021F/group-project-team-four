package com.example.skoolplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.skoolplanner.databinding.ActivityMainBinding
import com.example.skoolplanner.databinding.CreateExamBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: CreateExamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateExamBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.selectTime.setOnClickListener {
            openTimePicker()
        }

        binding.selectDate.setOnClickListener {
            openDatePicker()
        }
    }

    private fun openDatePicker() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Exam Date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        picker.show(supportFragmentManager, "tag")

        picker.addOnPositiveButtonClickListener {
            val date = "Date: ${picker.headerText}"
            binding.selectDate.text = date
        }
    }

    private fun openTimePicker() {

        val picker = MaterialTimePicker.Builder()
            .setInputMode(INPUT_MODE_KEYBOARD)
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(12)
            .setMinute(10)
            .setTitleText("Select Exam time")
            .build()
        picker.show(supportFragmentManager, "Tag")
        picker.addOnPositiveButtonClickListener {
            val hour: Int = picker.hour
            var newHour: Int = hour % 12
            val minute: Int = picker.minute
            if (newHour == 0) {
                newHour = 12
            }

            val clockPeriod: String = if (hour >= 12) {
                "PM"
            } else {
                "AM"
            }


            val time = "Time: $newHour:$minute $clockPeriod"
            binding.selectTime.text = time
        }
    }


}