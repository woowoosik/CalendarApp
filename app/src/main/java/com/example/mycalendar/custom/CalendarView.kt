package com.example.mycalendar.custom

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.mycalendar.R
import com.example.mycalendar.databinding.ActivityMainBinding
import com.example.mycalendar.utils.CalendarUtils.Companion.WEEKS_PER_MONTH
import com.example.mycalendar.viewmodel.MainViewModel
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants

class CalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.calendarViewStyle,
    @StyleRes defStyleRes: Int = R.style.Calendar_CalendarViewStyle
) : ViewGroup(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    private var _height: Float = 0f

    private lateinit var mainViewModel: MainViewModel

    init {
        context.withStyledAttributes(attrs, R.styleable.CalendarView, defStyleAttr, defStyleRes) {
            _height = getDimension(R.styleable.CalendarView_dayHeight, 0f)
        }


    }

    /**
     * Measure
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {


      /*  val h = paddingTop + paddingBottom + max(suggestedMinimumHeight, (_height * WEEKS_PER_MONTH).toInt())
       // val h = paddingTop + paddingBottom +(_height * WEEKS_PER_MONTH).toInt()


        Log.e("onMeasure", "${h} ${ widthMeasureSpec}  ${ heightMeasureSpec }")

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)


        Log.e("onMeasure", "size :  ${widthMeasureSpec} ${widthSize}  :  ${heightMeasureSpec} ${heightSize}")
        Log.e("onMeasure", "mode :  ${widthMode}  ${heightMode}")


        setMeasuredDimension(getDefaultSize(suggestedMinimumWidth, widthMeasureSpec),
            getDefaultSize(suggestedMinimumHeight, heightMeasureSpec))
*/

        // 측정된 폭과 높이를 출력해 보자


        // 측정된 폭과 높이를 출력해 보자
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        val w_mode = MeasureSpec.getMode(widthMeasureSpec)
        val h_mode = MeasureSpec.getMode(heightMeasureSpec)

        // 패딩값을 측정값의 10%를 주어 뺀다.

        Log.e("onMeasure", "width$width height$height")


        setMeasuredDimension(width, height)
    }

    /**
     * Layout
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.e("onLarout", " ${width}  ${height}")
        //val iWidth = (width / DAYS_PER_WEEK).toFloat()
        val iWidth = (width / DateTimeConstants.DAYS_PER_WEEK).toFloat()
        val iHeight = (height / WEEKS_PER_MONTH).toFloat()
        Log.e(
            "CustomView-onLayout",
            "rect : (x, y, w, h) :  ${left}  ${top}    ${(right - left)}  ${bottom - top}"
        )

        var index = 0
        children.forEach { view ->
            /*val left = (index % DAYS_PER_WEEK) * iWidth
            val top = (index / DAYS_PER_WEEK) * iHeight
*/

            val left = (index % DateTimeConstants.DAYS_PER_WEEK) * iWidth
            val top = (index / DateTimeConstants.DAYS_PER_WEEK) * iHeight


            Log.e("onLarout_foreach", "${index} ${left}  ${top}")

            view.layout(left.toInt(), top.toInt(), (left + iWidth).toInt(), (top + iHeight).toInt())

            index++
        }
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val left = left
        val top = top
        val width = width
        val height = height
        val mwidth = measuredWidth
        val mheight = measuredHeight
        Log.e(
            "CustomView-onDraw",
            "rect : (x, y, w, h, mw, mh) : $left $top $width $height $mwidth $mheight"
        )
    }

    /**
     * 달력 그리기 시작한다.
     * @param firstDayOfMonth   한 달의 시작 요일
     * @param list              달력이 가지고 있는 요일과 이벤트 목록 (총 42개)
     */
    fun initCalendar(firstDayOfMonth: DateTime, list: List<DateTime>, viewModel: MainViewModel) {
        list.forEach {
            addView(DayItemView(
                context = context,
                date = it,
                firstDayOfMonth = firstDayOfMonth,
                viewModel = viewModel
            ))
        }
    }

}