package com.family.cloud.album.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Rect
import android.util.AttributeSet
import cn.sccl.xlibrary.utils.XLogUtils
import com.family.cloud.album.R
import com.google.android.material.button.MaterialButton


/**
 * ====================================================
 * @User :caobin
 * @Date :2021/10/26 22:24
 * @Desc :焦点选中效果自定义view
 * ====================================================
 */
class IconTextButton : MaterialButton {

    companion object {
        val TAG = "IconTextButton"
    }

    private var mSelectedBgColor: Int = 0
    private var mUnSelectBgColor: Int = 0
    private var mSelectedIconColor: Int = 0
    private var mUnSelectIconColor: Int = 0
    private var mSelectedTextColor: Int = 0
    private var mUnSelectTextColor: Int = 0

    private var enableScale: Boolean = false

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
        isFocusable = true
        insetBottom = 0
        insetTop = 0

        attrs?.let {
            val typeArray = context.obtainStyledAttributes(attrs, R.styleable.IconTextButton)
            mSelectedBgColor = typeArray.getColor(R.styleable.IconTextButton_selected_bg_color, 0)
            mSelectedTextColor =
                typeArray.getColor(R.styleable.IconTextButton_selected_text_color, 0)
            mSelectedIconColor =
                typeArray.getColor(R.styleable.IconTextButton_selected_icon_color, 0)

            mUnSelectBgColor =
                typeArray.getColor(R.styleable.IconTextButton_un_selected_bg_color, 0)
            mUnSelectTextColor =
                typeArray.getColor(R.styleable.IconTextButton_un_selected_text_color, 0)
            mUnSelectIconColor =
                typeArray.getColor(R.styleable.IconTextButton_un_selected_icon_color, 0)
            typeArray.recycle()
        }

        val aaa = ColorStateList(arrayOf(),intArrayOf())


        if (mUnSelectTextColor != 0 && mSelectedTextColor != 0) {
            setTextColor(
                ColorStateList(
                    arrayOf(
                        intArrayOf(-android.R.attr.state_focused),
                        intArrayOf(android.R.attr.state_focused)
                    ), intArrayOf(mUnSelectTextColor, mSelectedTextColor)
                )
            )
        }
        if (mSelectedBgColor != 0 && mUnSelectBgColor != 0) {
            backgroundTintList = ColorStateList(
                arrayOf(
                    intArrayOf(-android.R.attr.state_focused),
                    intArrayOf(android.R.attr.state_focused)
                ), intArrayOf(mUnSelectBgColor, mSelectedBgColor)
            ).apply {
                enableScale=false
            }
        }

        if (mSelectedIconColor != 0 && mUnSelectIconColor != 0) {
            iconTint = ColorStateList(
                arrayOf(
                    intArrayOf(-android.R.attr.state_focused),
                    intArrayOf(android.R.attr.state_focused)
                ), intArrayOf(mUnSelectIconColor, mSelectedIconColor)
            )
        }


    }

    override fun onFocusChanged(
        focused: Boolean, direction: Int,
        previouslyFocusedRect: Rect?
    ) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)

        XLogUtils.i("$TAG onFocusChanged $focused")
    }

}