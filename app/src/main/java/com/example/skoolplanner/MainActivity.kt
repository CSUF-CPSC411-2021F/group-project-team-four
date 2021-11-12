package com.example.skoolplanner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.skoolplanner.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.nav_host)
        //NavigationUI.setupActionBarWithNavController(this, navController)

        val bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setupWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host)
        return navController.navigateUp()
    }

    override fun onBackPressed() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val selectedItemId = bottomNavigationView.selectedItemId;
        if(selectedItemId != R.id.todayView) {
            bottomNavigationView.selectedItemId = R.id.todayView
        }
        else {
            super.onBackPressed()
        }
    }
}