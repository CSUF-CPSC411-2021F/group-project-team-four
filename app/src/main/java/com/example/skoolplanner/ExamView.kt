package com.example.skoolplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.skoolplanner.adapter.ExamItemAdapter
import com.example.skoolplanner.data.Datasource
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

        val myDataset = Datasource().loadExams()
        val recyclerView = binding.examList
        recyclerView.adapter = ExamItemAdapter(myDataset as MutableList<Exam>)

        binding.newExamButton.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_examView_to_createExam)
        }
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}