package com.example.mycalendar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mycalendar.database.Schedule
import com.example.mycalendar.database.ScheduleDao
import com.example.mycalendar.database.ScheduleDatabase
import org.joda.time.DateTime

class Repository(private val scheduleDao: ScheduleDao) {

    // private val scheduleDao = scheduleDao()
   // val allData: LiveData<List<Schedule>> = scheduleDao.getAll()
    private var dbData:LiveData<List<Schedule>> = MutableLiveData()
/*

    companion object{
        private var sInstance: Repository? = null
        fun getInstance(database: ScheduleDatabase): Repository {
            return sInstance
                ?: synchronized(this){
                    val instance = Repository(database)
                    sInstance = instance
                    instance
                }
        }
    }
*/

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(scheduleDao: ScheduleDao) =
            instance ?: synchronized(this) {
                instance ?: Repository(scheduleDao).also { instance = it }
            }

    }

    suspend fun getDate(d:String) {

        Log.e("Repository","$dbData")

    }

    fun repositoryTest(d:String): List<Schedule> = scheduleDao.getDate(d)

    fun setRoomData(d:String, s:String, c:String) = scheduleDao.setInsertData(d,s,c)

    fun updateSchedule(id: Int,d:String, s:String, c:String) = scheduleDao.setUpdateData(id,d,s,c)


}