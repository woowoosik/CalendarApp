package com.example.mycalendar

import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.joda.time.DateTime

class CalendarAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    /* 달의 첫 번째 Day timeInMillis*/
    private var start: Long = DateTime().withDayOfMonth(1).withTimeAtStartOfDay().millis

    override fun getItemCount(): Int = Int.MAX_VALUE

    // fragment생성
    // fragment 생성할 때는 newInstance를 쓴다.
    override fun createFragment(position: Int): CalendarFragment {
        val millis = getItemId(position)
        Log.e("createFragment","${DateTime(millis)}")
        return CalendarFragment.newInstance(millis)
    }

    // 해당 position의 고유 id 반환
   override fun getItemId(position: Int): Long
            = DateTime(start).plusMonths(position - START_POSITION).millis

    // id가 유효한 id인지 판단
    override fun containsItem(itemId: Long): Boolean {
        val date = DateTime(itemId)
        return date.dayOfMonth == 1 && date.millisOfDay == 0
    }

    companion object {
        const val START_POSITION = Int.MAX_VALUE / 2
    }
}