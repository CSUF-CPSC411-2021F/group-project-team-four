package com.example.skoolplanner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skoolplanner.databinding.TodayViewItemBinding
import com.example.skoolplanner.model.Exam

class TodayViewAdapter(
    private var dataset: MutableList<com.example.skoolplanner.model.Exam> = mutableListOf<com.example.skoolplanner.model.Exam>()
) : RecyclerView.Adapter<TodayViewAdapter.TodayViewHolder>() {

    // ScheduleViewHolder holds the data of each element in the RecyclerView
    class TodayViewHolder(private val binding: TodayViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val activityDueDate = binding.ActivityDueDate
        val activityName = binding.ActivityName
        val className = binding.ClassName
        val activityTime = binding.ActivityTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodayViewHolder {
        val binding = TodayViewItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TodayViewHolder(binding)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: TodayViewHolder, position: Int) {
        val item = dataset[position]
        holder.activityDueDate.text = item.date
        holder.activityName.text = item.name
        holder.className.text = item.className
        holder.activityTime.text = item.time
    }
}