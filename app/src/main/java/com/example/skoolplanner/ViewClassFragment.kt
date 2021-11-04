package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.skoolplanner.databinding.FragmentViewClassBinding
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass.
 * Use the [ViewClassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewClassFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentViewClassBinding.inflate(layoutInflater)

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