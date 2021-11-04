package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.skoolplanner.databinding.FragmentEditClassBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EditClassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditClassFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditClassBinding.inflate(layoutInflater)

        binding.doneButton.setOnClickListener { view: View ->
            view.findNavController().navigate(EditClassFragmentDirections.actionEditClassFragmentToViewClassFragment())
        }

        return binding.root
    }
}