package com.example.skoolplanner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.skoolplanner.database.Activity
import com.example.skoolplanner.databinding.ActivityItemBinding

/**
 * An adapter for the RecyclerView in the activity_list layout file. It uses DiffCallback for
 * better performance and a listener to handle taps on each item.
 */
class ActivityListAdapter(val clickListener: ActivityListener) : ListAdapter<Activity,
        ActivityListAdapter.ItemViewHolder>(ActivityDiffCallback()) {

    /**
     * An inner class for storing data of each element in the RecylcerView
     */
    class ItemViewHolder(val binding: ActivityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Assigns an Activity object and clickListener to the ItemViewHolder
         */
        fun bind(item: Activity, clickListener: ActivityListener) {
            binding.activity = item
            binding.clickListener = clickListener
        }
    }

    /**
     * Creates a View for visualizing an individual item in the RecylcerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityItemBinding.inflate(layoutInflater, parent, false)

        return ItemViewHolder(binding)
    }

    /**
     * This function is called when the RecyclerView displays an individual item
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

/**
 * Handles the list in the RecyclerView
 */
class ActivityDiffCallback : DiffUtil.ItemCallback<Activity>() {

    /**
     * Checks to see if two elements have the same Id
     */
    override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
        return oldItem.activityId == newItem.activityId
    }

    /**
     * Checks all properties of an Activity to see if any Activity objects are the same
     */
    override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
        return oldItem.name == newItem.name && oldItem.dueDate == newItem.dueDate
    }
}

/**
 * A listener with a lambda function (clickListener) as its parameter. This class is called when an
 * item in the RecyclerView is tapped
 */
class ActivityListener(val clickListener: (activityId: Long) -> Unit) {
    fun onClick(activity: Activity) = clickListener(activity.activityId)
}