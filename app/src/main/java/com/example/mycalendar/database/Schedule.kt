package com.example.mycalendar.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    var date: String,
    var title: String,
    var content: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}