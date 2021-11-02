
package com.family.cloud.album.view

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import cn.sccl.xlibrary.utils.XLogUtils
import com.family.cloud.album.R
import com.family.cloud.album.utils.AppUtils

class IconTextButton : LinearLayout {

    companion object {
        val TAG = "BaseIconTextButton"
        val DEFAULT_VALUE = -100
    }

    private var mSelectBgColor: Int = DEFAULT_VALUE
    private var mUnSelectBgColor: Int = DEFAULT_VALUE
    private var mSelectIconResId: Int = DEFAULT_VALUE
    private var mUnSelectIconResId: Int = DEFAULT_VALUE
    private var mSelectTextColor: Int = DEFAULT_VALUE
    private var mUnSelectTextColor: Int = DEFAULT_VALUE
    private var mLayoutId: Int = DEFAULT_VALUE
    private var mRound: Float = 0.0f
    private var mScaleValue: Float = 0.0f
    private var mText: String? = null
    private var enableScale: Boolean = false

    private lateinit var mTextView: TextView
    private lateinit var mImageView: ImageView


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
        clipChildren=true
        clipToPadding=false
        gravity=Gravity.CENTER
        attrs?.let {
            val typeArray = context.obtainStyledAttributes(attrs, R.styleable.IconTextButton)
            mSelectBgColor = typeArray.getColor(R.styleable.IconTextButton_selected_bg_color, 0)
            mSelectTextColor =
                typeArray.getColor(R.styleable.IconTextButton_selected_text_color, 0)
            mSelectIconResId =
                typeArray.getResourceId(R.styleable.IconTextButton_selected_icon_res_id, 0)

            mUnSelectBgColor =
                typeArray.getColor(R.styleable.IconTextButton_un_selected_bg_color, 0)
            mUnSelectTextColor =
                typeArray.getColor(R.styleable.IconTextButton_un_selected_text_color, 0)
            mUnSelectIconResId =
                typeArray.getResourceId(R.styleable.IconTextButton_un_selected_icon_res_id, 0)
            mRound = typeArray.getDimension(R.styleable.IconTextButton_icon_round, 0f)
            mText = typeArray.getString(R.styleable.IconTextButton_itb_text)
            enableScale = typeArray.getBoolean(R.styleable.IconTextButton_itb_scale, false)
            mLayoutId = typeArray.getResourceId(R.styleable.IconTextButton_itb_layout, 0)
            mScaleValue = typeArray.getFloat(R.styleable.IconTextButton_itb_scale_value, 1.0f)
            typeArray.recycle()

            LayoutInflater.from(getContext()).inflate(mLayoutId, this)
            mTextView = findViewById(R.id.tv_icon_name)
            mImageView = findViewById(R.id.iv_icon)
        }



        if (!mText.isNullOrEmpty()) {
            mTextView.text = mText
        }
        mTextView.setTextColor(mUnSelectTextColor)
        mImageView.setImageResource(mUnSelectIconResId)
        background = AppUtils.getShapeDrawable(mUnSelectBgColor,mRound)
    }


    override fun onFocusChanged(
        focused: Boolean, direction: Int,
        previouslyFocusedRect: Rect?
    ) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (focused) {
            scaleXy(true)
            mTextView.setTextColor(mSelectTextColor)
            mImageView.setImageResource(mSelectIconResId)
            background = AppUtils.getShapeDrawable(mSelectBgColor,mRound)
        } else {
            scaleXy(false)
            mTextView.setTextColor(mUnSelectTextColor)
            mImageView.setImageResource(mUnSelectIconResId)
            background = AppUtils.getShapeDrawable(mUnSelectBgColor,mRound)
        }
    }


    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        return super.dispatchKeyEvent(event)
    }

    private fun scaleXy(enable: Boolean) {
        if (!enableScale)return
        if (enable) {
            scaleX = mScaleValue
            scaleY = mScaleValue
        } else {
            scaleX = 1.0f
            scaleY = 1.0f
        }
    }
}