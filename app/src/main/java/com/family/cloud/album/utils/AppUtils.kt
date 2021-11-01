package com.family.cloud.album.utils

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import androidx.annotation.ColorInt

/**
 * ====================================================
 * @User :caobin
 * @Date :2021/10/30 22:00
 * @Desc :
 * ====================================================
 */
object AppUtils {


    fun getAppVersion(): String {
        return "6.5.1"
    }


    fun getShapeDrawable(@ColorInt color: Int, round: Float): ShapeDrawable {
        val round = floatArrayOf(round, round, round, round, round, round, round, round)
        return ShapeDrawable().apply {
            shape = RoundRectShape(round, null, null)
            paint.color = color
        }
    }

}