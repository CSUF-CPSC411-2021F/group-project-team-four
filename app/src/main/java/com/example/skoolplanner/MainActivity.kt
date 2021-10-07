package com.example.skoolplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.skoolplanner.adapter.ScheduleAdapter
import com.example.skoolplanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    /**
     * Sets up the layout and interactions with the main screen of the application.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}