package com.example.skoolplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

//        binding.addButton.setOnClickListener { view: View ->
////            // Adds the class name to the mutableList.
////            scheduleAdapter.dataset.add("${binding.className.text}" as ClassSchedule)
////            scheduleAdapter.dataset.add("${binding.classDays.text}" as ClassSchedule)
////            scheduleAdapter.dataset.add("${binding.classTimeStart.text}" as ClassSchedule)
////            scheduleAdapter.dataset.add("${binding.classTimeEnd.text}" as ClassSchedule)
//
//            this.findNavController().navigate(
//                AddClassFragmentDirections
//                    .actionAddClassFragmentToViewClassFragment()
//            )
//        }

        binding.cancelButton.setOnClickListener { view: View ->
            this.findNavController().navigate(
                AddClassFragmentDirections
                    .actionAddClassFragmentToViewClassFragment()
            )
        }

        return binding.root
    }
}