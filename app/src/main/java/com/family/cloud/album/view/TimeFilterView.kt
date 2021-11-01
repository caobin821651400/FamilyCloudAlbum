package com.family.cloud.album.view

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.sccl.xlibrary.adapter.XRecyclerViewAdapter
import cn.sccl.xlibrary.adapter.XViewHolder
import cn.sccl.xlibrary.kotlin.setVisible
import cn.sccl.xlibrary.utils.XLogUtils
import com.family.cloud.album.R
import java.util.*

/**
 * ====================================================
 * @User :caobin
 * @Date :2021/10/31 18:03
 * @Desc :
 * ====================================================
 */
class TimeFilterView : LinearLayout {

    companion object {
        val TAG = "TimeFilterView"
    }

    private lateinit var yearRv: TvRecyclerView
    private lateinit var monthRv: TvRecyclerView
    private lateinit var dayRv: TvRecyclerView
    private lateinit var mYearAdapter: TimeFilterAdapter
    private lateinit var mMonthRvAdapter: TimeFilterAdapter
    private lateinit var mDayAdapter: TimeFilterAdapter
    private var visibilityListener: ((b: Boolean) -> Unit)? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_time_filter, this)
        yearRv = findViewById(R.id.year_rv)
        monthRv = findViewById(R.id.month_rv)
        dayRv = findViewById(R.id.day_rv)

        mYearAdapter = TimeFilterAdapter(yearRv)
        mMonthRvAdapter = TimeFilterAdapter(monthRv)
        mDayAdapter = TimeFilterAdapter(dayRv)

        yearRv.adapter = mYearAdapter
        monthRv.adapter = mMonthRvAdapter
        dayRv.adapter = mDayAdapter

        val yearList = ArrayList<TimeFilterBean>()
        for (i in 0..9) {
            yearList.add(TimeFilterBean("2021"))
        }
        mYearAdapter.dataLists = yearList


        val monthList = ArrayList<TimeFilterBean>()
        for (i in 0..9) {
            monthList.add(TimeFilterBean("02"))
        }
        mMonthRvAdapter.dataLists = monthList

        val dayList = ArrayList<TimeFilterBean>()
        for (i in 0..9) {
            dayList.add(TimeFilterBean("12"))
        }
        mDayAdapter.dataLists = dayList

        initListener()
    }


    private fun initListener() {
        yearRv.onBorderListener = object : TvRecyclerView.SimpleBorderListener() {
            override fun onTopBorder(): Boolean {
                setVisible(false)
                return true
            }

            override fun onBottomBorder(): Boolean {
                return true
            }

            override fun onLeftBorder(): Boolean {
                return true
            }

            override fun onRightBorder(): Boolean {
                monthRv.requestFocus()
                return true
            }
        }
        monthRv.onBorderListener = object : TvRecyclerView.SimpleBorderListener() {
            override fun onTopBorder(): Boolean {
                setVisible(false)
                return true
            }

            override fun onBottomBorder(): Boolean {
                return true
            }

            override fun onLeftBorder(): Boolean {
                yearRv.requestFocus()
                return true
            }
            override fun onRightBorder(): Boolean {
                dayRv.requestFocus()
                return true
            }
        }

        dayRv.onBorderListener = object : TvRecyclerView.SimpleBorderListener() {
            override fun onTopBorder(): Boolean {
                setVisible(false)
                return true
            }

            override fun onBottomBorder(): Boolean {
                return true
            }

            override fun onRightBorder(): Boolean {
                return true
            }
            override fun onLeftBorder(): Boolean {
                monthRv.requestFocus()
                return true
            }
        }
    }

    inner class TimeFilterAdapter(rv: RecyclerView) :
        XRecyclerViewAdapter<TimeFilterBean>(rv, R.layout.item_time_filter) {

        override fun bindData(holder: XViewHolder, data: TimeFilterBean, position: Int) {
            (holder.itemView as TextView).text = data.text
        }
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        visibilityListener?.invoke(visibility == View.VISIBLE)
    }


    fun visibilityChange(listener: (b: Boolean) -> Unit) {
        visibilityListener = listener
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        val keyCode = event.keyCode
        if (event.action != KeyEvent.ACTION_DOWN) return true
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> {
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
            }
            KeyEvent.KEYCODE_DPAD_CENTER -> {
            }
            KeyEvent.KEYCODE_BACK -> {
                setVisible(false)
                return true
            }
        }
        return super.dispatchKeyEvent(event)
    }


    data class TimeFilterBean(
        val text: String = ""
    )
}