package com.family.cloud.album.login

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import cn.sccl.xlibrary.kotlin.dp2px
import com.family.cloud.album.MainActivity
import com.family.cloud.album.R
import com.family.cloud.album.base.BaseMVVMActivity
import com.family.cloud.album.databinding.ActivityLoginBinding


/**
 * ====================================================
 * @User :caobin
 * @Date :2021/10/30 21:02
 * @Desc :
 * ====================================================
 */
class LoginActivity : BaseMVVMActivity<LoginViewModule, ActivityLoginBinding>() {


    companion object {
        val bannerList = arrayListOf(
            R.mipmap.banner_login,
            R.mipmap.banner_login,
            R.mipmap.banner_login
        )
    }

    private val mHandler by lazy { MyHandler() }
    private var vpIndex = 0


    @SuppressLint("SetTextI18n")
    override fun initUI() {
//        Handler(Looper.getMainLooper()).postDelayed({
//            go2Main()
//            finish()
//        }, 1000)

        initVp()

        mHandler.sendEmptyMessageDelayed(1, 3000)
    }

    override fun initEvent() {
    }

    private fun initVp() {
        binding.vpContent.adapter = VpAdapter()
        binding.indicator.setViewPager(binding.vpContent)
    }

    fun go2Main() {
        launchActivity(MainActivity::class.java, null)
    }


    private inner class MyHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            vpIndex++
            if (vpIndex == bannerList.size) {
                vpIndex = 0
            }
            binding.vpContent.setCurrentItem(vpIndex, false)
            mHandler.sendEmptyMessageDelayed(1, 3000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacksAndMessages(null)
    }

    inner class VpAdapter : PagerAdapter() {

        override fun getCount(): Int {
            return bannerList.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(view: ViewGroup, position: Int, `object`: Any) {
            view.removeView(`object` as View)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = ImageView(container.context)
            val size = dp2px(272f)
            imageView.layoutParams = ViewGroup.LayoutParams(size, size)
            imageView.setImageResource(bannerList[position])
            imageView.setBackgroundColor(Color.RED)
            container.addView(imageView)
            return imageView
        }
    }
}