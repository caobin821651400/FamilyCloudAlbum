package com.family.cloud.album.video

import cn.sccl.xlibrary.utils.XLogUtils
import com.family.cloud.album.base.BaseMVVMFragment
import com.family.cloud.album.databinding.FragmentMusicBinding
import com.family.cloud.album.databinding.FragmentVideoBinding

class VideoFragment: BaseMVVMFragment<VideoViewModule, FragmentVideoBinding>() {



    override fun initUI() {

    }

    override fun initEvent() {
        binding.albumButton.setOnClickListener {
            XLogUtils.d(mViewModule.aa)
        }
    }


}