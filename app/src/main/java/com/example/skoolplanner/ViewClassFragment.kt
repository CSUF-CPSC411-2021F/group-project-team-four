package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.skoolplanner.databinding.FragmentViewClassBinding
import androidx.navigation.findNavController
import com.example.skoolplanner.adapter.ScheduleAdapter
import com.example.skoolplanner.data.Datasource

/**
 * A simple [Fragment] subclass.
 * Use the [ViewClassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewClassFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentViewClassBinding.inflate(layoutInflater)
        val classDataset = Datasource().loadClassSchedules()

        // The RecyclerView adapter is created here, scheduleAdapter, and it is assigned to the RecyclerView.
        var scheduleAdapter = ScheduleAdapter(classDataset as MutableList<ClassSchedule>)
        binding.scheduleList.adapter = scheduleAdapter

        binding.addButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_viewClassFragment_to_addClassFragment)
        }

        binding.editButton.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_viewClassFragment_to_editClassFragment)
        }

        return binding.root
    }
}