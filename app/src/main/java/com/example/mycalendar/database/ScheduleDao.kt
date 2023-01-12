package com.example.mycalendar.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface ScheduleDao {
    @Insert
    fun insert(schedule: Schedule)

    @Update
    fun update(schedule: Schedule)

    @Delete
    fun delete(schedule: Schedule)

    @Query("SELECT * FROM Schedule")
    fun getAll(): List<Schedule>

    @Query("INSERT INTO SCHEDULE(date, title, content) VALUES('2022-9-28','테스트제목2','테스트내용2')")
    fun setTestData()

    @Query("SELECT * FROM Schedule WHERE date = :d")
    fun getDate(d:String):List<Schedule>


    @Query("INSERT INTO SCHEDULE(date, title, content) VALUES(:d ,:t,:c)")
    fun setInsertData(d:String, t:String, c:String)

    @Query("UPDATE SCHEDULE SET date = :d, title = :s, content = :c WHERE id=:id")
    fun setUpdateData(id: Int,d:String, s:String, c:String)

    @Query("DELETE FROM SCHEDULE WHERE id=:id")
    fun setDeleteData(id: Int)



}