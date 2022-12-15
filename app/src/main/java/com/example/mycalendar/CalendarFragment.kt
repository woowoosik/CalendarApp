package com.example.mycalendar

import android.app.Application
import android.database.DatabaseUtils
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.mycalendar.database.ScheduleDatabase
import com.example.mycalendar.databinding.ActivityMainBinding
import com.example.mycalendar.databinding.FragmentCalendarBinding
import com.example.mycalendar.factory.MainViewModelFactory
import com.example.mycalendar.utils.CalendarUtils.Companion.getMonthList
import com.example.mycalendar.viewmodel.FragmentViewModel
import com.example.mycalendar.viewmodel.MainViewModel
// import kotlinx.android.synthetic.main.fragment_calendar.view.*
import org.joda.time.DateTime

class CalendarFragment : Fragment() {

    private var millis: Long = 0L
    private lateinit var fragmentBinding : FragmentCalendarBinding
    private lateinit var mainViewModel : MainViewModel

    private lateinit var fragmentViewModel: FragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            millis = it.getLong(MILLIS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // val view = inflater.inflate(R.layout.fragment_calendar, container, false)

      /*  val dao = context?.let { ScheduleDatabase.getInstance(it) }!!.scheduleDao()
        val repository = Repository.getInstance(dao)
        val factory = MainViewModelFactory(repository)
*/
        mainViewModel = ViewModelProvider(activity as ViewModelStoreOwner)[MainViewModel::class.java]

        fragmentViewModel = ViewModelProvider(this)[FragmentViewModel::class.java]

        // fragmentBinding = FragmentCalendarBinding.inflate(layoutInflater)

        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        fragmentBinding.fragmentViewModel = fragmentViewModel
        fragmentBinding.lifecycleOwner = this

        // fragmentBinding = DataBindingUtil.setContentView(this, R.layout.fragment_calendar)

        //fragmentBinding.millis.text = DateTime(millis).toString("yyyy-MM")

         fragmentViewModel.dateChange(DateTime(millis).toString("yyyy-MM"))

        Log.e("onCreateView text", "${fragmentBinding.millis.text}")
        Log.e("onCreateView","   ${DateTime(millis)} ${getMonthList(DateTime(millis))}")
        // 이번 달 1일 0시 , 저번달 짤리는 날
        fragmentBinding.calendarView.initCalendar(DateTime(millis), getMonthList(DateTime(millis)), mainViewModel)

        // recyclerview 초기값을 위한 클릭이벤트
        mainViewModel.dayClick(DateTime().toString("yyyy-MM-dd"))

        return fragmentBinding.root
    }



    companion object {

        private const val MILLIS = "MILLIS"

        fun newInstance(millis: Long) = CalendarFragment().apply {
            arguments = Bundle().apply {
                putLong(MILLIS, millis)
            }
        }
    }
}