package com.example.skoolplanner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skoolplanner.databinding.ClassScheduleListBinding

class ScheduleAdapter (
    var dataset: MutableList<com.example.skoolplanner.database.ClassSchedule> = mutableListOf<com.example.skoolplanner.database.ClassSchedule>()
    ) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

        // ScheduleViewHolder holds the data of each element in the RecyclerView
        class ScheduleViewHolder(private val binding: ClassScheduleListBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val position = binding.Position
            val secId = binding.SecId
            val className = binding.ClassName
            val classDays = binding.ClassDays
            val classStartTime = binding.ClassStartTime
            val classEndTime = binding.ClassEndTime
        }

        // Creates the view holder object and passes the necessary arguments.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {

            val binding = ClassScheduleListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return ScheduleViewHolder(binding)
        }

        // Assigns the values from the data to the view holder's view elements.
        override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
            val item = dataset[position]

            holder.position.text = (position + 1).toString() + "."
            holder.secId.text = item.secId.toString()
            holder.className.text = item.className
            holder.classDays.text = item.classDays
            holder.classStartTime.text = item.classStartTime
            holder.classEndTime.text = item.classEndTime
        }

        // Returns the number of elements in the dataset.
        override fun getItemCount() = dataset.size
}