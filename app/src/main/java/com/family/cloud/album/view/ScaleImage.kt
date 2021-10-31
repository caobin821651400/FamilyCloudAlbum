package com.family.cloud.album.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.family.cloud.album.R

/**
 * ====================================================
 * @User :caobin
 * @Date :2021/10/31 17:21
 * @Desc :
 * ====================================================
 */
class ScaleImage : AppCompatImageView {


    private var mScaleValue: Float = 0.0f

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
        attrs?.let {
            val typeArray = context.obtainStyledAttributes(attrs, R.styleable.ScaleView)
            mScaleValue = typeArray.getFloat(R.styleable.ScaleView_si_scale_value, 1.0f)
            typeArray.recycle()
        }
    }


    override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
        scaleMe(hasFocus())
    }

    fun scaleMe(b: Boolean) {
        if (b) {
            scaleX = mScaleValue
            scaleY = mScaleValue
        } else {
            scaleX = 1.0f
            scaleY = 1.0f
        }
    }
}