package com.example.skoolplanner

import java.sql.Time
import java.util.*

// Exam class creates exam and quiz objects
// Exam view will display all upcoming quizzes and tests
// Users will receive periodical notifications to study

class Exam(name: String, date: String, time: String, description: String) {

    val examName = name
    val examDate = date
    val examTime = time
    val examDescription = description

    fun sendReminder() {
        TODO("Create function to send reminders before exam")
    }

}

fun main() {
    // Store all upcoming exams and quizzes in a list.
    val examList = mutableListOf<Exam>()
    val midterm1 = Exam("CPSC 411 Midterm", "09/30/2021", "4:00PM", "Review Codelabs 1-5")
    examList.add(midterm1)
}