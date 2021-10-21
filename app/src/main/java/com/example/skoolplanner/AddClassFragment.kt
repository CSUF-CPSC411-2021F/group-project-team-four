package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.skoolplanner.databinding.FragmentAddClassBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddClassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddClassFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {val binding = DataBindingUtil.inflate<FragmentAddClassBinding>(inflater,
        R.layout.fragment_view_class,container,false)

        binding.addButton.setOnClickListener { view: View ->
            view.findNavController().navigate(AddClassFragmentDirections.actionAddClassFragmentToViewClassFragment2())
        }

        binding.cancelButton.setOnClickListener { view: View ->
            view.findNavController().navigate(AddClassFragmentDirections.actionAddClassFragmentToViewClassFragment2())
        }

        return binding.root
    }
}