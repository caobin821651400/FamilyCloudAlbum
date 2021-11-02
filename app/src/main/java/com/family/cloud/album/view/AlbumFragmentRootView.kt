package com.family.cloud.album.view

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.constraintlayout.widget.ConstraintLayout

class AlbumFragmentRootView : ConstraintLayout {

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

    }


    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (onBorderListener == null
            || event.action != KeyEvent.ACTION_DOWN
        ) {
            return super.dispatchKeyEvent(event)
        }
        var intercept = false
        when (event.keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> {
                intercept = onBorderListener!!.onTopBorder()
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                intercept = onBorderListener!!.onLeftBorder()
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                intercept = onBorderListener!!.onRightBorder()
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                intercept = onBorderListener!!.onBottomBorder()
            }
        }
        return intercept || super.dispatchKeyEvent(event)
    }


    var onBorderListener: OnBorderListener? = null

    interface OnBorderListener {
        fun onLeftBorder(): Boolean
        fun onTopBorder(): Boolean
        fun onRightBorder(): Boolean
        fun onBottomBorder(): Boolean
    }

}