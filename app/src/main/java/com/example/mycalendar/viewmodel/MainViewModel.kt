package com.example.mycalendar.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mycalendar.Repository
import com.example.mycalendar.database.Schedule
import com.example.mycalendar.database.ScheduleDao
import com.example.mycalendar.database.ScheduleDatabase
import com.example.mycalendar.factory.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.joda.time.DateTime

class MainViewModel(private val repository: Repository) : ViewModel() {
    var date : MutableLiveData<String> = MutableLiveData()
    var title : MutableLiveData<String> = MutableLiveData()
    var content : MutableLiveData<String> = MutableLiveData()

    var detailDate : MutableLiveData<String> = MutableLiveData()
    var detailTitle : MutableLiveData<String> = MutableLiveData()
    var detailContent : MutableLiveData<String> = MutableLiveData()


    private lateinit var list : List<Schedule>


    private var recyclerData = arrayListOf<Schedule>()


    var itemPosition : MutableLiveData<Int> = MutableLiveData()
    fun itemClick(c:Int){
        Log.e("itemClick", "$c")
    }






    val itemData = MutableLiveData<Schedule>()

    fun getItemData(date:String, title:String, content:String){
        itemData.value = Schedule(date, title, content)
        Log.e("MainViewModel", "getItemData  ${itemData.value}")
    }

    fun setItemData() = itemData

    /*private val testData = arrayListOf(Schedule("2022-10-25","타이틀1","공식방송날"),
        Schedule("2022-10-27","정식오픈날","오픈날"),
        Schedule("2022-10-27","쉬는날","꿀"))*/


    val scheduleLiveData = MutableLiveData<List<Schedule>>()

    //var listText : MutableLiveData<String> = MutableLiveData()
/*
    private val readGetData: LiveData<List<Schedule>>
    private val repository:Repository*/


    init {
        date.value = DateTime().toString("yyyy-MM-dd")
        title.value=""
        content.value =""

        detailDate.value = ""
        detailTitle.value = ""
        detailContent.value = ""

        /*val scheduleDao = ScheduleDatabase.getInstance(application)!!.scheduleDao()
        repository = Repository(scheduleDao) //이니셜라이즈 해줍니다.
        readGetData = repository.allData// readAlldata는 repository에서 만들어줬던 livedata입니다.*/
    }


    fun viewmodelTest(){
        Log.e("viewmodel test"," 테스트 성공 ")
    }

    fun dayClick(day:String){
        Log.e("MainViewModel", "${date.value}  ${day} 클릭")
        date.value = day

        viewModelScope.launch(Dispatchers.IO) { //코루틴 활성화 dispatcherIO는 백그라운드에서 실행

            list = repository.repositoryTest(day)

            scheduleLiveData.postValue(repository.repositoryTest(day))

            for(dt in list){
                Log.e("MainViewModel__" ," ${dt.date}  ${dt.title}  ${dt.content}")


            /* val text = listText.value + dt.date+"\n"
                listText.postValue(text)
                Log.e("MainViewModel__" ," ${listText.value}")*/
            }



        }



    }


    fun clickTest(){
        Log.e("MainViewModel_3", "@@@@@  ${date.value} 클릭")
    }

    fun mainBtnClick(){
        Log.e("main_btn_click", "@@@@@  메인 버튼1  ${date.value}  클릭")

        viewModelScope.launch(Dispatchers.IO) { //코루틴 활성화 dispatcherIO는 백그라운드에서 실행

            repository.setRoomData("2022-11-23",
                DateTime().toString("HH:mm")+" 공식방송 공식방송 공식방송 공식방송 공식방송공식방송 공식방송 공식방송 공식방송 공식방송공식방송 공식방송 공식방송 공식방송 공식방송공식방송 공식방송 공식방송 공식방송 공식방송" +
                                            "공식방송 공식방송 공식방송 공식방송 공식방송공식방송 공식방송 공식방송 공식방송 공식방송",
                                        "ㄲㄲㄲㄲㄲㄲdkdkfk카나다라다마앙라다아차파각다자자아차라파가가망라ㅏ라나나아파ㅏㅏ가ㅏ나아라낭ㄹㄴ" +
                                                "ㄲㄲㄲㄲㄲㄲdkdkfk카나다라다마앙라다아차파각다자자아차라파가가망라ㅏ라나나아파ㅏㅏ가ㅏ나아라낭ㄹㄴ" +
                                                "ㄲㄲㄲㄲㄲㄲdkdkfk카나다라다마앙라다아차파각다자자아차라파가가망라ㅏ라나나아파ㅏㅏ가ㅏ나아라낭ㄹㄴ" +
                                                "ㄲㄲㄲㄲㄲㄲdkdkfk카나다라다마앙라다아차파각다자자아차라파가가망라ㅏ라나나아파ㅏㅏ가ㅏ나아라낭ㄹㄴ")

        }

        if(date.value == "2022-11-23"){

            dayClick("2022-11-23")
        }
    }

    fun addSchedule(){
        Log.e("addSchedule", "@@@@@ ${detailDate.value} $detailTitle $detailContent ")

        viewModelScope.launch(Dispatchers.IO) { //코루틴 활성화 dispatcherIO는 백그라운드에서 실행

            // val date = detailDate.value
            val dDate = "2022-12-20"
            val dTitle = detailTitle.value
            val dContent = detailContent.value

            detailDate.postValue("")
            detailTitle.postValue("")
            detailContent.postValue("")

            Log.e("addSchedule", " $dDate  $dTitle  $dContent")
            repository.setRoomData("${dDate}",
                DateTime().toString("HH:mm")+" ${dTitle}",
                "${dContent}")

            if(dDate == date.value.toString()){
                scheduleLiveData.postValue(repository.repositoryTest(dDate))
            }

        }


    }



/*

    suspend fun select(d:String){
        repository = Repository(mDatabase)
        Repository.getInstance(ScheduleDatabase.getInstance(application)!!)
        val list =
            withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
                Repository.getDate(d)
            }

        for(schedule in list){
            date.value += "${schedule.date}  ${schedule.title}  ${schedule.content}\n"
        }
    }
*/


/*

    fun dateSelect(d:String){
        viewModelScope.launch(Dispatchers.IO) { //코루틴 활성화 dispatcherIO는 백그라운드에서 실행
            repository.getDate(d) //repository에 adduser함수 불러오기
        }

        Log.e("MainViewModel","$readGetData")
    }

*/

    class Factory(private val application : Application) : ViewModelProvider.Factory { // factory pattern
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val dao = ScheduleDatabase.getInstance(application)!!.scheduleDao()
            return MainViewModel(Repository.getInstance(dao)!!) as T
        }
    }


}