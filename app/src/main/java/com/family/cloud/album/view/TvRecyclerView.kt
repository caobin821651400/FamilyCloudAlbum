package com.family.cloud.album.view

import android.content.Context
import android.util.AttributeSet
import android.view.FocusFinder
import android.view.KeyEvent
import android.view.View
import androidx.leanback.widget.VerticalGridView
import cn.sccl.xlibrary.utils.XLogUtils

class TvRecyclerView : VerticalGridView {


    var onBorderListener: OnBorderListener? = null

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


    private fun handleKey(event: KeyEvent): Boolean {
        if (scrollState != SCROLL_STATE_IDLE
            || childCount <= 0
            || onBorderListener == null
        ) {
            return true
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
        return intercept
    }

    private fun moveFocus(event: KeyEvent): Boolean {
        var hasNextFocus = false
        val direction = getDirection(event)
        if (direction == -1) {
            return false
        }
        val curFocus = findFocus()
        val nextFocus = FocusFinder.getInstance().findNextFocus(this, curFocus, direction)
        var nextFocusItemView: View? = null

        nextFocus?.let { nextFocusItemView = findContainingItemView(it) }
//        if (!isNextFocusUseful(curFocus, nextFocus, direction)) {
//            return false
//        }
        nextFocusItemView?.let { hasNextFocus = requestFocusInternal(nextFocus, direction) }
        XLogUtils.e("hasNextFocus  $hasNextFocus")
        return hasNextFocus
    }

    private fun requestFocusInternal(view: View?, direction: Int): Boolean {
        view?.let { return it.requestFocus(direction) }
        return false
    }

    private fun getDirection(event: KeyEvent): Int {
        when (event.keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> return View.FOCUS_UP
            KeyEvent.KEYCODE_DPAD_LEFT -> return View.FOCUS_LEFT
            KeyEvent.KEYCODE_DPAD_RIGHT -> return View.FOCUS_RIGHT
            KeyEvent.KEYCODE_DPAD_DOWN -> return View.FOCUS_DOWN
        }
        return -1
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        val action = event.action
        if (action != KeyEvent.ACTION_DOWN) {
            return super.dispatchKeyEvent(event)
        }
        var intercept = moveFocus(event)
        intercept = intercept || handleKey(event)
        return intercept || super.dispatchKeyEvent(event)
    }

//    private val mRect1 by lazy { Rect() }
//    private val mRect2 by lazy { Rect() }
//    private fun isNextFocusUseful(currView: View?, nextView: View?, direction: Int): Boolean {
//        if (direction == -1 || nextView == null) {
//            return false
//        }
//        if (currView == null) {
//            return true
//        }
//
//        mRect1.setEmpty()
//        mRect2.setEmpty()
//        offsetDescendantRectToMyCoords(currView, mRect1)
//        offsetDescendantRectToMyCoords(nextView, mRect2)
//
//        when (direction) {
//            View.FOCUS_RIGHT, View.FOCUS_LEFT -> {
//                return !(mRect1.top > mRect2.top + nextView.measuredHeight
//                        || mRect1.top + currView.measuredHeight < mRect2.top)
//            }
//            View.FOCUS_UP, View.FOCUS_DOWN -> {
//                return !(mRect1.left > mRect2.left + nextView.measuredWidth
//                        || mRect1.left + currView.measuredWidth < mRect2.left)
//            }
//        }
//        return false
//    }

    interface OnBorderListener {
        fun onLeftBorder(): Boolean
        fun onTopBorder(): Boolean
        fun onRightBorder(): Boolean
        fun onBottomBorder(): Boolean
    }

    open class SimpleBorderListener : OnBorderListener {
        override fun onLeftBorder(): Boolean = false
        override fun onTopBorder(): Boolean = false
        override fun onRightBorder(): Boolean = false
        override fun onBottomBorder(): Boolean = false
    }
}