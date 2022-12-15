package com.example.mycalendar.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycalendar.Repository
import com.example.mycalendar.database.Schedule
import com.example.mycalendar.database.ScheduleDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joda.time.DateTime

class FragmentViewModel: ViewModel() {
    val date : MutableLiveData<String> = MutableLiveData()

    private val dbData : MutableLiveData<List<Schedule>> = MutableLiveData()
  /*  private val readGetData: LiveData<List<Schedule>>
    private val repository: Repository*/
    init {
        date.value = DateTime().toString("yyyy-MM-dd")

       /* val scheduleDao = ScheduleDatabase.getInstance(application)!!.scheduleDao()
        repository = Repository(scheduleDao)
        readGetData = repository.allData
        */
    }

    fun dateChange(d:String){
        date.value = d
        Log.e("FragmentViewModel", " ${date.value}")
    }
/*

    fun dateSelect(d:String){
        viewModelScope.launch(Dispatchers.IO) { //코루틴 활성화 dispatcherIO는 백그라운드에서 실행
            repository.getDate(d) //repository에 adduser함수 불러오기
        }

        Log.e("MainViewModel","$readGetData")
    }

*/

}