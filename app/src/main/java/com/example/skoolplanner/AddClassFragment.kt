package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
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

        binding.addButton.setOnClickListener { view: View ->
            view.findNavController().navigate(AddClassFragmentDirections.actionAddClassFragmentToViewClassFragment())
        }

        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController().navigate(AddClassFragmentDirections.actionAddClassFragmentToViewClassFragment())
        }

        return binding.root
    }
}