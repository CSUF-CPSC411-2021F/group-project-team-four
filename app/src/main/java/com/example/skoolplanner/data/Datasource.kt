package com.example.skoolplanner.data

import com.example.skoolplanner.database.ClassSchedule
import com.example.skoolplanner.model.Exam

class Datasource {
    fun loadExams(): List<Exam> {
        return mutableListOf<Exam>(
            Exam("CPSC 440", "Midterm", "10/13/2021", "5:30 PM", "Chapter 1-4"),
            Exam(
                "CPSC 411",
                "Midterm",
                "10/14/2021",
                "4:00 PM",
                "RecyclerView, DataBinding, App Navigation, etc."
            ),
            Exam(
                "CPSC 483",
                "Midterm",
                "10/21/2021",
                "7:00 PM",
                "K-fold CV, Vectorization, Linear Regression, etc."
            ),
            Exam(
                "MATH 250B",
                "Midterm II",
                "10/25/2021",
                "2:00PM",
                "Vector Spaces, Inner Product Spaces, Basis/Dimension"
            )
        )
    }

    fun loadClassSchedules(): List<ClassSchedule> {
        return mutableListOf<ClassSchedule>(
            ClassSchedule(
                "CPSC 411-01",
                "Tuesday Thursday",
                "4:00 PM",
                "5:15 PM"
            ),
            ClassSchedule(
                "CPSC 353-03",
                "Thursday",
                "7:00 PM",
                "9:45 PM"
            )
        )
    }
}