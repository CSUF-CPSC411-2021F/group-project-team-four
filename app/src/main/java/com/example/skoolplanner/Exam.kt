package com.example.skoolplanner

// Exam class creates exam and quiz objects
// Exam view will display all upcoming quizzes and tests
// Users will receive periodical notifications to study

class Exam(className: String, name: String, date: String, time: String, description: String) {

    val className = className
    val examName = name
    val examDate = date
    val examTime = time
    val examDescription = description

    fun sendReminder() {
        TODO("Create function to send reminders before exam")
    }

}

fun createExam(
    className: String,
    name: String,
    date: String,
    time: String,
    description: String
): Exam {
    return Exam(className, name, date, time, description)
}

fun deleteExam() {

}

fun modifyExam() {

}

fun main() {
    // Store all upcoming exams and quizzes in a list.
    val examList = mutableListOf<Exam>()
    val midterm1 = createExam("CPSC 411", "Midterm", "09/30/2021", "4:00PM", "Review Codelabs 1-5")
    examList.add(midterm1)
    print("The ${examList[0].className} ${examList[0].examName} will take place on ${examList[0].examDate} at ${examList[0].examTime}")
}