package com.example.skoolplanner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.skoolplanner.database.ClassSchedule
import com.example.skoolplanner.databinding.ScheduleItemBinding

/**
 * An adapter for the RecyclerView in the fragment_view_class layout file. It uses DiffCallback for
 * better performance and a listener to handle taps on each item.
 */
class ScheduleAdapter(val clickListener: ScheduleListener) : ListAdapter<ClassSchedule,
        ScheduleAdapter.ItemViewHolder>(ScheduleDiffCallback()) {

    /**
     * Inner class used to store data about each element in the RecyclerView. We provide a binding
     * to make it easy to access elements of the layout.
     */
    class ItemViewHolder(val binding: ScheduleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Assign a ClassSchedule object and clickListener to the ItemViewHolder
         */
        fun bind(item: ClassSchedule, clickListener: ScheduleListener) {
            binding.schedule = item
            binding.clickListener = clickListener
        }
    }

    /**
     * Creates a View to visualize one element in the RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // We use an inflater based on the parent (ViewClassFragment) and create an
        // ItemViewHolder with binding to the layout to simplify access.
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ScheduleItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    /**
     * The function is called whenever the RecyclerView displays a specific element. It provides
     * access to the ItemViewHolder and its position.
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Assign the corresponding element from the data and the click listener.
        holder.bind(getItem(position), clickListener)
    }
}

/**
 * Manages a RecyclerView list using the Eugene W. Myers's difference algorithm to reduce processing.
 */
class ScheduleDiffCallback : DiffUtil.ItemCallback<ClassSchedule>() {
    /**
     * We use secId because it is a unique ID referring to a single element.
     */
    override fun areItemsTheSame(oldItem: ClassSchedule, newItem: ClassSchedule): Boolean {
        return oldItem.secId == newItem.secId
    }

    /**
     * We check all properties to check equality between ClassSchedule objects.
     */
    override fun areContentsTheSame(oldItem: ClassSchedule, newItem: ClassSchedule): Boolean {
        return oldItem.className == newItem.className && oldItem.classDays == newItem.classDays &&
                oldItem.classStartTime == newItem.classStartTime && oldItem.classEndTime == newItem.classEndTime
    }
}

/**
 * Listener that accepts a lambda function clickListener. It will be called when an element of the
 * RecyclerView is clicked/tapped.
 */
class ScheduleListener(val clickListener: (secId: Long) -> Unit) {
    fun onClick(schedule: ClassSchedule) = clickListener(schedule.secId)
}