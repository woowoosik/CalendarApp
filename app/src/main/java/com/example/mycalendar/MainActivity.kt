package com.example.mycalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycalendar.database.ScheduleDatabase
import com.example.mycalendar.databinding.ActivityMainBinding
import com.example.mycalendar.factory.MainViewModelFactory
import com.example.mycalendar.fragment.AddFragment
import com.example.mycalendar.fragment.DetailFragment
import com.example.mycalendar.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding
    private lateinit var db : ScheduleDatabase

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // databinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // branch 2 test 주석
/*      12.8 주석
        val dao = ScheduleDatabase.getInstance(application)!!.scheduleDao()
        val repository = Repository.getInstance(dao)
        val factory = MainViewModelFactory(repository)*/

        viewModel = ViewModelProvider(this,MainViewModel.Factory(application))[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

       /*
       viewModel.date.observe(this, Observer {
            viewModel.clickTest()
        })
*/

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = MainRecyclerAdapter(
            emptyList()
        )
        binding.recyclerview.adapter = adapter

        adapter.setItemClickListener(object: MainRecyclerAdapter.OnItemClickListener{
            override fun onItemClick(v:View, position: String, position2: String, position3: String) {
                Log.e("Main", "@@@@@@@@@@@@@@@@@@@@@@@@@@")
              /*  val intent = Intent(this@MainActivity, DetailPage::class.java)

                startActivity(intent)*/
                viewModel.getItemData(position,position2,position3)
                Log.e("p1", "$position")

                Log.e("p2", "$position2")

                Log.e("p3", "$position3")
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, DetailFragment())
                    .addToBackStack(null)
                    .commit()
            }
        })



        viewModel.scheduleLiveData.observe(this, Observer{
            (binding.recyclerview.adapter as MainRecyclerAdapter).setData(it)
        })

        // db 생성
        /*db = ScheduleDatabase.getInstance(application)!!

        CoroutineScope(Dispatchers.Main).launch {
            CoroutineScope(Dispatchers.IO).async {
                db.scheduleDao().setTestData()
            }.await()

            var scheduleList = CoroutineScope(Dispatchers.IO).async {
                db.scheduleDao().getAll()
            }.await()

            for(schedule in scheduleList){
                Log.e("schedule"," ${schedule.date}  ${schedule.title}  ${schedule.content}")
            }
        }
*/



        // viewpager2
        val calendarAdapter = CalendarAdapter(this)

        binding.mainViewPager2.adapter = calendarAdapter
        binding.mainViewPager2.setCurrentItem(CalendarAdapter.START_POSITION, false)


        binding.addFloating.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, AddFragment())
                .addToBackStack(null)
                .commit()
        }

        // viewModel.dayClick("2022-09-29")


    }



}