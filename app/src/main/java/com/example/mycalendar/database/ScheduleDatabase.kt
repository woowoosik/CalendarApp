package com.example.mycalendar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycalendar.viewmodel.MainViewModel

@Database(entities = [Schedule::class], version = 1 )
abstract class ScheduleDatabase() : RoomDatabase() {
    abstract fun scheduleDao() : ScheduleDao

    companion object {
        private var instance: ScheduleDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ScheduleDatabase? {
            if (instance == null) {
                synchronized(ScheduleDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ScheduleDatabase::class.java,
                        "schedule-database"
                    )
                        .build()
                }
            }
            return instance
        }
    }
}