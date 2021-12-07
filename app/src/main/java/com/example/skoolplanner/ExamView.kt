package com.example.skoolplanner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.skoolplanner.adapter.ActivityListAdapter
import com.example.skoolplanner.adapter.ActivityListener
import com.example.skoolplanner.adapter.ExamItemAdapter
import com.example.skoolplanner.data.Datasource
import com.example.skoolplanner.database.Activity
import com.example.skoolplanner.database.ActivityDatabase
import com.example.skoolplanner.databinding.FragmentExamViewBinding
import com.example.skoolplanner.model.Exam


/**
 * Fragment Class to display list of Exam Objects for Exam View.
 */

private var _binding: FragmentExamViewBinding? = null
private val binding get() = _binding!!

class ExamView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExamViewBinding.inflate(inflater, container, false)

        //val myDataset = Datasource().loadExams()
        //val recyclerView = binding.examList
        //recyclerView.adapter = ExamItemAdapter(myDataset as MutableList<Exam>)

        //binding.newExamButton.setOnClickListener {
          //  it.findNavController()
            //    .navigate(R.id.action_examView_to_createExam)
        //}

        val application = requireNotNull(this.activity).application
        val dataSource = ActivityDatabase.getInstance(application).activityDao
        val viewModelFactory = ActivityViewModelFactory(dataSource, application)
        val activityViewModel = ViewModelProvider(this, viewModelFactory).get(ActivityViewModel::class.java)

        var activityAdapter = ActivityListAdapter(ActivityListener {
                activityId ->
            this.findNavController().navigate(
                ExamViewDirections.actionExamViewToActivityItemFragment(activityId)
            )
        })

        binding.examList.adapter = activityAdapter

        activityViewModel.activityList.observe(viewLifecycleOwner, Observer { it ->
            it?.let { it ->
                val newExamList = mutableListOf<Activity>()

                for(activity in it)
                {
                    if(activity.isExam)
                    {
                        newExamList.add(activity)
                    }
                }

                activityAdapter.submitList(newExamList)
            }
        })

        return binding.root
    }

    fun getExamActivities(activityType: String, binding: FragmentExamViewBinding) {
        val application = requireNotNull(this.activity).application
        val dataSource = ActivityDatabase.getInstance(application).activityDao
        val viewModelFactory = ActivityViewModelFactory(dataSource, application)
        val activityViewModel = ViewModelProvider(this, viewModelFactory).get(ActivityViewModel::class.java)

        var activityAdapter = ActivityListAdapter(ActivityListener {
                activityId ->
            this.findNavController().navigate(
                ExamViewDirections.actionExamViewToActivityItemFragment(activityId)
            )
        })

        binding.examList.adapter = activityAdapter

        activityViewModel.activityList.observe(viewLifecycleOwner, Observer { it ->
            it?.let { it ->
                val newExamList = mutableListOf<Activity>()

                for(activity in it)
                {
                    if(activity.name == "Exam")
                    {
                        newExamList.add(activity)
                    }
                }

                activityAdapter.submitList(newExamList)
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}