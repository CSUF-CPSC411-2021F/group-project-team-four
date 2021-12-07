package com.example.skoolplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.skoolplanner.adapter.ScheduleAdapter
import com.example.skoolplanner.adapter.ScheduleListener
import com.example.skoolplanner.database.ClassScheduleDatabase
import com.example.skoolplanner.databinding.FragmentViewClassBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ViewClassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewClassFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create data binding
        val binding: FragmentViewClassBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_class, container, false)

        // Get reference to the application
        val application = requireNotNull(this.activity).application

        // Retrieve ClassSchedule data access object.
        val dataSource = ClassScheduleDatabase.getInstance(application).classScheduleDao

        // Create a factory that generates IntersectionViewModels connected to the database.
        val viewModelFactory = ScheduleViewModelFactory(dataSource, application)

        // Generate an ScheduleViewModel using the factory.
        val scheduleViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ScheduleViewModel::class.java)

        // Connect the ScheduleViewModel with the variable in the layout
        binding.scheduleViewModel = scheduleViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        // Provide a lambda function that is called when the RecyclerView item is selected.
        var scheduleAdapter = ScheduleAdapter(ScheduleListener { secId ->
            // Navigate to the schedule view and provide the id of the section referenced
            // by the select RecyclerView item.
            this.findNavController().navigate(
                ViewClassFragmentDirections
                    .actionViewClassFragmentToScheduleItemFragment(secId)
            )
        })

        // Attach schedule adapter.
        binding.scheduleList.adapter = scheduleAdapter

        // Submit an updated list to the schedule listAdapter whenever it changes. Take note
        // classScheduleList is a LiveData object.
        scheduleViewModel.classScheduleList.observe(viewLifecycleOwner, Observer {
            it?.let {
                scheduleAdapter.submitList(it)
            }
        })
        return binding.root
    }
}