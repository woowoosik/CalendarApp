package com.example.mycalendar.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mycalendar.Repository
import com.example.mycalendar.database.Schedule
import com.example.mycalendar.database.ScheduleDatabase
import kotlinx.coroutines.*
import org.joda.time.DateTime

class MainViewModel(private val repository: Repository) : ViewModel() {
    var date : MutableLiveData<String> = MutableLiveData()
    var title : MutableLiveData<String> = MutableLiveData()
    var content : MutableLiveData<String> = MutableLiveData()


    var detailData = MutableLiveData<Schedule>()

    var updateData = MutableLiveData<Schedule>()

    // var addData = MutableLiveData<Schedule>()



/*

    var addDate : MutableLiveData<String> = MutableLiveData()
    var addTitle : MutableLiveData<String> = MutableLiveData()
    var addContent : MutableLiveData<String> = MutableLiveData()
*/

   /* var detailDate : MutableLiveData<String> = MutableLiveData()
    var detailTitle : MutableLiveData<String> = MutableLiveData()
    var detailContent : MutableLiveData<String> = MutableLiveData()*/

    private lateinit var list : List<Schedule>


   // val itemData = MutableLiveData<Schedule>()

    fun getItemData(position:String, title:String, content:String){
       // itemData.value = Schedule(date, title, content)

        // itemData.value = scheduleLiveData.value?.get(position.toInt())
        detailData.postValue(scheduleLiveData.value?.get(position.toInt()))
        // Log.e("MainViewModel", "getItemData  ${itemData.value} ${scheduleLiveData.value?.get(position.toInt())}")
    }

    fun setItemData(position:String, title:String, content:String){
        // itemData.value = Schedule(date, title, content)
        // itemData.postValue(Schedule(position,title,content))
 }



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

        resetAddDate()
/*
        detailDate.value = ""
        detailTitle.value = ""
        detailContent.value = ""*/

        /*val scheduleDao = ScheduleDatabase.getInstance(application)!!.scheduleDao()
        repository = Repository(scheduleDao) //이니셜라이즈 해줍니다.
        readGetData = repository.allData// readAlldata는 repository에서 만들어줬던 livedata입니다.*/
    }

    fun dayClick(day:String){
        Log.e("MainViewModel", "${date.value}  ${day} 클릭")
        date.value = day

        viewModelScope.launch(Dispatchers.IO) { //코루틴 활성화 dispatcherIO는 백그라운드에서 실행

             list = repository.repositoryTest(day)

            scheduleLiveData.postValue(list)

            for(dt in list){
                Log.e("MainViewModel__" ,"${dt.id} ${dt.date}  ${dt.title}  ${dt.content}")

            }

        }
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

    private fun resetAddDate(){
      /*  addDate.postValue("")
        addTitle.postValue("")
        addContent.postValue("")*/
    }

    private val _addComplate : MutableLiveData<Event<Boolean>> = MutableLiveData()
    val addComplate : LiveData<Event<Boolean>> = _addComplate


    fun addSchedule(d:String, t:String, c:String){
      //  Log.e("addSchedule", "@@@@@ ${d} $t $c ")

        viewModelScope.launch(Dispatchers.IO) { //코루틴 활성화 dispatcherIO는 백그라운드에서 실행

           // Log.e("addScheduel","${addData.value}")
            // val date = detailDate.value
            /*val dDate = addData.value?.date
            val dTitle = addData.value?.title
            val dContent = addData.value?.content


            addData.postValue(Schedule("","",""))
            */

            val dDate = d
            val dTitle = t
            val dContent = c

/*
            detailDate.postValue("")
            detailTitle.postValue("")
            detailContent.postValue("")*/


            Log.e("addSchedule", " $dDate  $dTitle  $dContent")
            repository.setRoomData("${dDate}",
                DateTime().toString("HH:mm")+" ${dTitle}",
                "${dContent}")


            if(dDate == date.value.toString()){
                scheduleLiveData.postValue(repository.repositoryTest(dDate))

            }

            _addComplate.postValue(Event(true))
        }

    }


    private val _updateComplate : MutableLiveData<Event<Boolean>> = MutableLiveData()
    val updateComplate : LiveData<Event<Boolean>> = _updateComplate

    fun updateSchdule(d:String, t:String, c:String) {

        viewModelScope.launch(Dispatchers.IO) { //코루틴 활성화 dispatcherIO는 백그라운드에서 실행

            Log.e("updateSchedule", "${date.value}   $d $t $c")
            val dId = detailData.value?.id
            val dDate = d
            val dTitle = t
            val dContent = c
/*
            detailDate.postValue("")
            detailTitle.postValue("")
            detailContent.postValue("")*/

            // updateData.postValue(Schedule("","",""))



            Log.e("updateSchedule2", "${date.value}   $d $t $c")
            if (dId != null) {
                repository.updateSchedule(
                    dId, "${dDate}",
                    DateTime().toString("HH:mm") + " ${dTitle}",
                    "${dContent}"
                )
            }



            Log.e("updateSchedule3", "${date.value}  $d $t $c")
            if (dDate == date.value.toString()) {

                Log.e("updateSchedule4", " $d $t $c")

                scheduleLiveData.postValue(repository.repositoryTest(dDate))

                Log.e("updateSchdule", " ${scheduleLiveData.value}")



                Log.e("updateSchedule5", " $d $t $c")
                Log.e("mainviewmodel", "update 버튼 detail: ${detailData.value}      update: ${updateData.value}")
                Log.e("mainviewmodel","${_updateComplate.value}")

                //itemData.value = scheduleLiveData.value?.get(dId!!)
            }


            detailData.postValue(Schedule(d,t,c))

            _updateComplate.postValue(Event(true))


            Log.e("updateSchedule6", "${date.value}  $d $t $c")
            // setItemData(dDate.toString(),dTitle.toString(),dContent.toString())

/*

            if (dDate != null) {
                itemData.value!!.date = dDate
            }else{
                itemData.value!!.date = ""
            }
            if (dTitle != null) {
                itemData.value!!.title = dTitle
            }else{
                itemData.value!!.title = ""
            }
            if (dContent != null) {
                itemData.value!!.content = dContent
            }else{
                itemData.value!!.content = ""
            }
*/


        }

    }


    private val _deleteComplate : MutableLiveData<Event<Boolean>> = MutableLiveData()
    val deleteComplate : LiveData<Event<Boolean>> = _deleteComplate

    fun deleteSchdule() {
        viewModelScope.launch(Dispatchers.IO) { //코루틴 활성화 dispatcherIO는 백그라운드에서 실행

            val dId = detailData.value?.id

            val dDate = detailData.value?.date

            if (dId != null) {
                repository.deleteSchedule(dId)
            }


            if (dDate == date.value.toString()) {
                scheduleLiveData.postValue(repository.repositoryTest(dDate))

            }

            _deleteComplate.postValue(Event(true))


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

    open class Event<out T>(private val content: T) {
        var hasBeenHandled = false
            private set

        fun getContentIfNotHandled(): T? {
            return if (hasBeenHandled) { // 이벤트가 이미 처리 되었다면
                null // null을 반환하고,
            } else { // 그렇지 않다면
                hasBeenHandled = true // 이벤트가 처리되었다고 표시한 후에
                content // 값을 반환합니다.
            }
        }

        /**
         * 이벤트의 처리 여부에 상관 없이 값을 반환합니다.
         */
        fun peekContent(): T = content
    }



}