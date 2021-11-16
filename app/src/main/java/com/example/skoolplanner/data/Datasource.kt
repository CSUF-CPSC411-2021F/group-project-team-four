package com.example.skoolplanner.data

import com.example.skoolplanner.database.Exam

class Datasource {
    fun loadExams(): List<Exam> {
        return mutableListOf<Exam>(
            Exam(1, "CPSC 440", "Midterm", "10/13/2021", "5:30 PM", "Chapter 1-4"),
            Exam(
                2,
                "CPSC 411",
                "Midterm",
                "10/14/2021",
                "4:00 PM",
                "RecyclerView, DataBinding, App Navigation, etc."
            ),
            Exam(
                3,
                "CPSC 483",
                "Midterm",
                "10/21/2021",
                "7:00 PM",
                "K-fold CV, Vectorization, Linear Regression, etc."
            ),
            Exam(
                4,
                "MATH 250B",
                "Midterm II",
                "10/25/2021",
                "2:00PM",
                "Vector Spaces, Inner Product Spaces, Basis/Dimension"
            )
        )
    }
}