package com.family.cloud.album.splash

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import com.family.cloud.album.MainActivity
import com.family.cloud.album.base.BaseMVVMActivity
import com.family.cloud.album.databinding.ActivitySplashBinding
import com.family.cloud.album.login.LoginActivity
import com.family.cloud.album.utils.AppUtils

/**
 * ====================================================
 * @User :caobin
 * @Date :2021/10/30 21:02
 * @Desc :
 * ====================================================
 */
class SplashActivity : BaseMVVMActivity<SplashViewModule, ActivitySplashBinding>() {


    @SuppressLint("SetTextI18n")
    override fun initUI() {
        binding.version.text = "客户端版本：${AppUtils.getAppVersion()}"
        Handler(Looper.getMainLooper()).postDelayed({
            go2Main()
            finish()
        }, 1000)
    }

    override fun initEvent() {
    }

    fun go2Main() {
//        launchActivity(MainActivity::class.java, null)
        launchActivity(LoginActivity::class.java, null)
    }
}