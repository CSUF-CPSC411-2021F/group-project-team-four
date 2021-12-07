package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.skoolplanner.adapter.ScheduleAdapter
import com.example.skoolplanner.data.Datasource
import com.example.skoolplanner.database.ClassSchedule
import com.example.skoolplanner.databinding.FragmentAddClassBinding

/**
 * A simple [Fragment] subclass.
 * Use the [AddClassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddClassFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddClassBinding.inflate(layoutInflater)
        // val classDataset = Datasource().loadClassSchedules()

//        // The RecyclerView adapter is created here, scheduleAdapter, and it is assigned to the RecyclerView.
//        var scheduleAdapter = ScheduleAdapter()

        binding.addButton.setOnClickListener { view: View ->
//            // Adds the class name to the mutableList.
//            scheduleAdapter.dataset.add("${binding.className.text}" as ClassSchedule)
//            scheduleAdapter.dataset.add("${binding.classDays.text}" as ClassSchedule)
//            scheduleAdapter.dataset.add("${binding.classTimeStart.text}" as ClassSchedule)
//            scheduleAdapter.dataset.add("${binding.classTimeEnd.text}" as ClassSchedule)

            view.findNavController()
                .navigate(R.id.action_addClassFragment_to_viewClassFragment)
        }

        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_addClassFragment_to_viewClassFragment)
        }

        return binding.root
    }
}