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
        // Retrieves the arguments passed from the RecyclerView
        val args = ActivityItemFragmentArgs.fromBundle(requireArguments())

        // Creates data binding
        val binding: ActivityItemFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.activity_item_fragment, container, false)

        // Gets reference to application
        val application = requireNotNull(this.activity).application

        // Gets the Activity Dao
        val dataSource = ActivityDatabase.getInstance(application).activityDao

        // Creates a factory that generates ActivityItemViewModels connected to the database
        val viewModelFactory =
            ActivityItemViewModelFactory(args.activityId, dataSource, application)

        // Creates an ActivityItemViewModel
        val activityItemViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ActivityItemViewModel::class.java)

        // Connects the ActivityViewModel to the variable in the layout
        binding.activityItemViewModel = activityItemViewModel

        // Assigns the lifecycle owner to this activity
        binding.lifecycleOwner = this

        return binding.root
    }

}