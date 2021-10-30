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
class IconTextButton : BaseIconTextButton {


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

    }

    override fun getLayoutId(): Int =R.layout.layout_image_text_button
}