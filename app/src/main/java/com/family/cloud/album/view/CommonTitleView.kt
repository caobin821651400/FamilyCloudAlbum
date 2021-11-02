package com.family.cloud.album.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import cn.sccl.xlibrary.kotlin.dp2px
import cn.sccl.xlibrary.kotlin.setVisible
import cn.sccl.xlibrary.utils.XDateUtils
import com.family.cloud.album.R

class CommonTitleView : ConstraintLayout {

    private val tvTitle: TextView by lazy { findViewById<TextView>(R.id.tv_title) }
    private val tvTime: TextView by lazy { findViewById<TextView>(R.id.tv_time) }
    private val line: View by lazy { findViewById<View>(R.id.line) }

    private var mTitle = ""
    private var isShowLine = true

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
        LayoutInflater.from(context).inflate(R.layout.layout_common_title, this)
        isFocusable = false

        attrs?.let {
            val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleView)
            typeArray.getString(R.styleable.CommonTitleView_ctv_title)?.let {
                mTitle = it
            }
            isShowLine = typeArray.getBoolean(R.styleable.CommonTitleView_ctv_show_line, true)
            typeArray.recycle()
        }

        if (mTitle.isNotEmpty()) {
            tvTitle.text = mTitle
        }
        line.setVisible(isShowLine)
        tvTime.text = XDateUtils.millis2String(System.currentTimeMillis(), "HH:mm")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        layoutParams.height=dp2px(72f)
    }
}