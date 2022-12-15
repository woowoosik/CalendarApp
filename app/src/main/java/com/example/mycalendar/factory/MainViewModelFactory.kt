package com.example.mycalendar.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mycalendar.Repository
import com.example.mycalendar.database.ScheduleDao


class MainViewModelFactory(private val repository: Repository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java)
            .newInstance(repository)
    }

}