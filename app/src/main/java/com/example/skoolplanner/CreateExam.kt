package com.example.skoolplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.skoolplanner.databinding.FragmentCreateExamBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_KEYBOARD
import com.google.android.material.timepicker.TimeFormat


/**
 * Fragment Class to Create Exam Object.
 */

private var _binding: FragmentCreateExamBinding? = null
private val binding get() = _binding!!

class CreateExam : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateExamBinding.inflate(inflater, container, false)

        binding.selectTime.setOnClickListener {
            openTimePicker()
        }

        binding.selectDate.setOnClickListener {
            openDatePicker()
        }

       binding.cancelExam.setOnClickListener {
             it.findNavController()
                .navigate(R.id.action_createExam_to_examView)
        }
        return binding.root
    }

    private fun openDatePicker() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("select exam date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        picker.show(childFragmentManager, "tag")

        picker.addOnPositiveButtonClickListener {
            val date = "date: ${picker.headerText}"
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
        picker.show(childFragmentManager, "Tag")
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