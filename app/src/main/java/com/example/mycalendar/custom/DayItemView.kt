package com.example.mycalendar.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import android.view.View.OnClickListener
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.lifecycle.get
import com.example.mycalendar.viewmodel.MainViewModel
import com.example.mycalendar.R
import com.example.mycalendar.databinding.ActivityMainBinding
import com.example.mycalendar.utils.CalendarUtils.Companion.getDateColor
import com.example.mycalendar.utils.CalendarUtils.Companion.isSameMonth
import org.joda.time.DateTime


class DayItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes private val defStyleAttr: Int = R.attr.itemViewStyle,
    @StyleRes private val defStyleRes: Int = R.style.Calendar_ItemViewStyle,
    private val date: DateTime = DateTime(),
    private val firstDayOfMonth: DateTime = DateTime(),
    private val viewModel: MainViewModel
) : View(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    private val bounds = Rect()

    private var paint: Paint = Paint()

    private  var rect = RectF(5F, 5F, 5F, 5F)



    init {
        /* Attributes */
        context.withStyledAttributes(attrs, R.styleable.CalendarView, defStyleAttr, defStyleRes) {
            val dayTextSize = getDimensionPixelSize(R.styleable.CalendarView_dayTextSize, 0).toFloat()

            /* 흰색 배경에 유색 글씨 */
            paint = TextPaint().apply {
                isAntiAlias = true
                textSize = dayTextSize
                color = getDateColor(date.dayOfWeek)

                if (!isSameMonth(date, firstDayOfMonth)) {
                    alpha = 50
                }
            }



        }




        setOnClickListener(
            OnClickListener {
                 Log.e("클릭 된 날짜 : ", "${date.toString("yyyy-MM-dd")}")


                viewModel.dayClick(date.toString("yyyy-MM-dd"))
                /*
                val vm = ViewModelProvider(ViewTreeViewModelStoreOwner.get(this)!!).get<MainViewModel>()
                //findViewTreeViewModelStoreOwner()!!

                //binding.viewModel!!.dayClick(date.toString("yyyy-MM-dd"))
                vm.dayClick(date.toString("yyyy-MM-dd"))*/
            }
        )





/*

        setOnClickListener(OnClickListener {

            MainActivity.newIn(date.toString("yyyy-MM-dd"))

        })

*/




    }




    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) return

        val date = date.dayOfMonth.toString()
        paint.getTextBounds(date, 0, date.length, bounds)
        Log.e("onDraw", "${bounds.width()}  ${bounds.height()} ${width}  ${height}")
        /*canvas.drawText(
            date,
            (width / 2 - bounds.width() / 2).toFloat() - 2,
            (height / 2 + bounds.height() / 2).toFloat(),
            paint
        )*/
        canvas.drawText(
            date,
            (width / 2 - bounds.width() / 2).toFloat() ,
            (height / 2 + bounds.height() / 2).toFloat(),
            paint
        )







    }
}