package com.example.skoolplanner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skoolplanner.R

class ScheduleAdapter (
    private val context: Context,
    var dataset: MutableList<String> = mutableListOf<String>()
    ) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {

        // ScheduleViewHolder holds the data of each element in the RecyclerView
        class ScheduleViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            val scheduleItemName: TextView = view.findViewById(R.id.schedule_list)
            val position: TextView = view.findViewById(R.id.position)
        }

        // Creates the view holder object and passes the necessary arguments.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {

            val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.class_schedule_list, parent, false)

            return ScheduleViewHolder(adapterLayout)
        }

        // Assigns the values from the data to the view holder's view elements.
        override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
            val item = dataset[position]

            holder.position.text = (position + 1).toString() + "."
            holder.scheduleItemName.text = item
        }

        // Returns the number of elements in the dataset.
        override fun getItemCount() = dataset.size
}