package com.example.skoolplanner.adapter

import android.app.Activity
import android.content.Context
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
         * Assign an class schedule object and clickListener to the ItemViewHolder
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
        // Assign the corresponding element from the data and the click listener. Take note getItem
        // is a function provided by ListAdapter.
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
     * We check all properties to check equality between Class Schedule objects.
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

//class ScheduleAdapter (
//    var dataset: MutableList<com.example.skoolplanner.database.ClassSchedule> = mutableListOf<com.example.skoolplanner.database.ClassSchedule>()
//    ) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
//
//        // ScheduleViewHolder holds the data of each element in the RecyclerView
//        class ScheduleViewHolder(private val binding: ClassScheduleListBinding) :
//            RecyclerView.ViewHolder(binding.root) {
//            val position = binding.Position
//            val secId = binding.SecId
//            val className = binding.ClassName
//            val classDays = binding.ClassDays
//            val classStartTime = binding.ClassStartTime
//            val classEndTime = binding.ClassEndTime
//        }
//
//        // Creates the view holder object and passes the necessary arguments.
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
//
//            val binding = ClassScheduleListBinding
//                .inflate(LayoutInflater.from(parent.context), parent, false)
//
//            return ScheduleViewHolder(binding)
//        }
//
//        // Assigns the values from the data to the view holder's view elements.
//        override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
//            val item = dataset[position]
//
//            holder.position.text = (position + 1).toString() + "."
//            holder.secId.text = item.secId.toString()
//            holder.className.text = item.className
//            holder.classDays.text = item.classDays
//            holder.classStartTime.text = item.classStartTime
//            holder.classEndTime.text = item.classEndTime
//        }
//
//        // Returns the number of elements in the dataset.
//        override fun getItemCount() = dataset.size
//}