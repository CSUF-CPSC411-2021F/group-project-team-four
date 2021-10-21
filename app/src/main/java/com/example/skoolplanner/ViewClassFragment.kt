package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.skoolplanner.databinding.FragmentViewClassBinding
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ViewClassFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewClassFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentViewClassBinding>(inflater,
            R.layout.fragment_view_class,container,false)

        binding.addButton.setOnClickListener { view: View ->
            view.findNavController().navigate(ViewClassFragmentDirections.actionViewClassFragment2ToAddClassFragment())
        }

        binding.editButton.setOnClickListener { view: View ->
            view.findNavController().navigate(ViewClassFragmentDirections.actionViewClassFragment2ToEditClassFragment())
        }

        return binding.root
    }
}