package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.skoolplanner.R
import com.example.skoolplanner.database.ClassScheduleDatabase
import com.example.skoolplanner.databinding.FragmentScheduleItemBinding
/**
 * Fragment that displays a single class schedule.
 */
class ScheduleItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve arguments passed from the RecyclerView
        val args = ScheduleItemFragmentArgs.fromBundle(
            requireArguments()
        )

        // Create data binding
        val binding: FragmentScheduleItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_schedule_item, container, false)

        // Get reference to this application
        val application = requireNotNull(this.activity).application

        // Retrieve ClassSchedule data access object.
        val dataSource = ClassScheduleDatabase.getInstance(application).classScheduleDao

        // Create a factory that generates an ScheduleViewModel connected to the database. The
        // secId passed from the RecyclerView is used to display the corresponding
        // information.
        val viewModelFactory =
            ScheduleItemViewModelFactory(args.secId, dataSource, application)

        // Generate an ScheduleViewModel using the factory.
        val scheduleItemViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ScheduleItemViewModel::class.java)

        // Connect the ScheduleViewModel with the variable in the layout
        binding.scheduleItemViewModel = scheduleItemViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        return binding.root
    }
}