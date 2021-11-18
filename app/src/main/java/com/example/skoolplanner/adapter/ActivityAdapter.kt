package com.example.skoolplanner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.skoolplanner.R
import com.example.skoolplanner.database.Activity
import com.example.skoolplanner.databinding.ActivityItemBinding

class ActivityAdapter(val clickListener: ActivityListener) : ListAdapter<Activity,
        ActivityAdapter.ItemViewHolder>(ActivityDiffCallback()) {

    class ItemViewHolder(val binding: ActivityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Activity, clickListener: ActivityListener) {
            binding.activity = item
            binding.clickListener = clickListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ActivityItemBinding.inflate(layoutInflater, parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class ActivityDiffCallback : DiffUtil.ItemCallback<Activity>() {
    override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
        return oldItem.activityId == newItem.activityId
    }

    override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
        return oldItem.name == newItem.name && oldItem.dueDate == newItem.dueDate
    }
}

class ActivityListener(val clickListener: (activityId: Long) -> Unit) {
    fun onClick(activity: Activity) = clickListener(activity.activityId)
}