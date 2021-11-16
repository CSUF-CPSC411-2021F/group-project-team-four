package com.example.skoolplanner.database

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExamDao {

    // insert an exam entity to a table in the database
    @Insert
    suspend fun insert(exam: Exam)

    // update an exam entity to a table in the database
    @Update
    suspend fun update(exam: Exam)

    // select a single exam entity from table using its examId
    @Query("SELECT * from exam_table WHERE examId = :key")
    fun get(key: Long): LiveData<ContactsContract.Profile>?

    // select all exam entities from table
    @Query("SELECT * from exam_table ORDER BY examId DESC")
    fun getAllExams(): LiveData<List<Exam>>

    // delete a single exam entity from table using its examId
    @Query("DELETE from exam_table WHERE examId = :key")
    fun delete(key: Long): LiveData<ContactsContract.Profile>?
}