package com.family.cloud.album.view

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import com.family.cloud.album.R

abstract class BaseIconTextButton : LinearLayout {

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
    private var mRound: Float = 0.0f
    private var mText: String? = null

    protected lateinit var mTextView: TextView
    protected lateinit var mImageView: ImageView

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
        LayoutInflater.from(getContext()).inflate(getLayoutId(), this)
        mTextView = findViewById(R.id.tv_icon_name)
        mImageView = findViewById(R.id.iv_icon)


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
            typeArray.recycle()
        }

        if (!mText.isNullOrEmpty()){
            mTextView.text = mText
        }
        mTextView.setTextColor(mUnSelectTextColor)
        mImageView.setImageResource(mUnSelectIconResId)
        background = getShapeDrawable(mUnSelectBgColor)
    }


    abstract fun getLayoutId(): Int


    override fun onFocusChanged(
        focused: Boolean, direction: Int,
        previouslyFocusedRect: Rect?
    ) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        background = if (focused) {
            mTextView.setTextColor(mSelectTextColor)
            mImageView.setImageResource(mSelectIconResId)
            getShapeDrawable(mSelectBgColor)
        } else {
            mTextView.setTextColor(mUnSelectTextColor)
            mImageView.setImageResource(mUnSelectIconResId)
            getShapeDrawable(mUnSelectBgColor)
        }
    }


    private fun getShapeDrawable(@ColorInt color: Int): ShapeDrawable {
        val round = floatArrayOf(mRound, mRound, mRound, mRound, mRound, mRound, mRound, mRound)
        return ShapeDrawable().apply {
            shape = RoundRectShape(round, null, null)
            paint.color = color
        }
    }
}