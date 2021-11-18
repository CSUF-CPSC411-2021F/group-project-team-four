package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.skoolplanner.R
import com.example.skoolplanner.database.ActivityDatabase
import com.example.skoolplanner.databinding.ActivityItemFragmentBinding
import edu.fullerton.ecs.mdap.intersectiondb.intersectionitem.ActivityItemViewModelFactory

/**
 * A fragment that displays an individual Activity object
 */
class ActivityItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val args = ActivityItemFragmentArgs.fromBundle(requireArguments())

        val binding: ActivityItemFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.activity_item_fragment, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = ActivityDatabase.getInstance(application).activityDao

        val viewModelFactory =
            ActivityItemViewModelFactory(args.activityId, dataSource, application)

        val activityItemViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ActivityItemViewModel::class.java)

        binding.activityItemViewModel = activityItemViewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}