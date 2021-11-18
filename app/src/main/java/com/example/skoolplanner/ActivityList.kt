package com.example.skoolplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.skoolplanner.adapter.ActivityAdapter
import com.example.skoolplanner.adapter.ActivityListener
import com.example.skoolplanner.database.ActivityDatabase
import com.example.skoolplanner.databinding.ActivityListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ActivityList.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActivityList : Fragment() {
    //override fun onCreate(savedInstanceState: Bundle?) {
    //    super.onCreate(savedInstanceState)
    //}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create data binding
        val binding: ActivityListBinding =
            DataBindingUtil.inflate(inflater, R.layout.activity_list, container, false)

        // Get reference to application
        val application = requireNotNull(this.activity).application

        // Gets the Activity Dao
        val dataSource = ActivityDatabase.getInstance(application).activityDao

        // Creates a factory that generates ActivityViewModels connected to the database
        val viewModelFactory = ActivityViewModelFactory(dataSource, application)

        // Creates an ActivityViewModel
        val activityViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ActivityViewModel::class.java)

        // Connect the ActivityViewModel to the variable in the activity_list layout
        binding.activityViewModel = activityViewModel

        // Assigns the lifecycle owner to the activity
        binding.lifecycleOwner = this

        var activityAdapter = ActivityAdapter(ActivityListener {
            activityId ->
            this.findNavController().navigate(
                ActivityListDirections
                    .actionActivityListToActivityItemFragment(activityId)
            )
        })
        // Navigates from the ActivityList view to the CreateActivity view when "Add Activity"
        // button is clicked on
        //binding.addActivityButton.setOnClickListener { view: View ->
          //  view.findNavController()
            //    .navigate(R.id.action_activityList_to_createActivity)
        //}

        // Lets the RecyclerView in activity_list know about the Activity adapter
        //val adapter = ActivityAdapter()

        // Associates the adapter with the RecyclerView
        binding.activityRecyclerview.adapter = activityAdapter

        activityViewModel.activityList.observe(viewLifecycleOwner, Observer {
            it?.let {
                activityAdapter.submitList(it)
            }
        })
        // Return a link to the layout root
        return binding.root
    }

    fun sortList() {

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ActivityList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ActivityList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}