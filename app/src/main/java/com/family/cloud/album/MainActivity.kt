package com.family.cloud.album

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import cn.sccl.xlibrary.utils.XLogUtils
import com.family.cloud.album.base.BaseMVVMActivity
import com.family.cloud.album.databinding.ActivityMainBinding

class MainActivity : BaseMVVMActivity<MainViewModule, ActivityMainBinding>(),
    View.OnFocusChangeListener {

    private lateinit var mNavController: NavController


    override fun initUI() {
        binding.myNavHostFragment.post {
            mNavController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        }

        binding.btnAlbum.postDelayed({binding.btnAlbum.requestFocus()},1000)

        binding.btnAlbum.onFocusChangeListener = this
        binding.btnVideo.onFocusChangeListener = this
        binding.btnMusic.onFocusChangeListener = this
        binding.btnSetting.onFocusChangeListener = this

    }

    override fun initEvent() {


    }

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        XLogUtils.e("View = $v hasFocus = $hasFocus")
        if (!hasFocus) return
        when (v) {
            binding.btnAlbum -> mNavController.navigate(R.id.albumFragment)
            binding.btnVideo -> mNavController.navigate(R.id.videoFragment)
            binding.btnMusic -> mNavController.navigate(R.id.musicFragment)
            binding.btnSetting -> mNavController.navigate(R.id.settingFragment)
        }
    }
}