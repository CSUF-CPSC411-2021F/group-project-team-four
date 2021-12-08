package com.example.skoolplanner.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Manages the database that stores the Class Schedule table and its entities.
 * The abstract class inherits from RoomDatabase()
 */
@Database(entities = [ClassSchedule::class], version = 1, exportSchema = false)
abstract class ClassScheduleDatabase : RoomDatabase() {
    // Data access object for the entity.
    abstract val classScheduleDao: ClassScheduleDao

    companion object {
        // Holds a reference to the database.
        @Volatile
        private var INSTANCE: ClassScheduleDatabase? = null // This class

        // Retrieve an instance of the database tied to the context (your application).
        fun getInstance(context: Context): ClassScheduleDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {

                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE

                // The first time the method is called, instance will be `null`, so we should create
                // a new database instance. The next time it is called, the database instance
                // already exists and does not need to be recreated.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ClassScheduleDatabase::class.java, // ClassSchedule database class
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                // Return database instance; smart cast to be non-null.
                return instance
            }
        }
    }
}